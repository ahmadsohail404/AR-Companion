package com.adityagupta.arcompanion

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adityagupta.arcompanion.databinding.ActivityImageDisplayBinding
import com.adityagupta.arcompanion.databinding.ActivityMeaningBinding
import kotlinx.coroutines.Delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class MeaningActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMeaningBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meaning)
        viewBinding = ActivityMeaningBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var word = intent.getStringExtra("word")
        Log.i("some", word!!)

        val WordsApi = RetrofitHelper.getInstance().create(Api::class.java)
        GlobalScope.launch {
            val result = WordsApi.getWordMeaning(word!!)
            if (result != null) {
                // Checking the results
                runOnUiThread(Runnable {
                    //TODO: Your job is here..!
                    viewBinding.progressBar.visibility = View.INVISIBLE
                    viewBinding.wordTitle.text = result!!.body()?.get(0)!!.word
                    Log.i("some", result.body()?.get(0)?.word!!)
                    viewBinding.wordPhonetic.text = result.body()?.get(0)?.phonetic
                    viewBinding.wordDef1.text =
                        result.body()?.get(0)?.meanings?.get(0)?.definitions?.get(0)?.definition

                    viewBinding.wordExample1.text =  result.body()?.get(0)?.meanings?.get(0)?.definitions?.get(0)?.example

                    Log.i("some", result.body().toString())
                })


            }
        }
    }


}