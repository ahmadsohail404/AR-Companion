package com.adityagupta.arcompanion

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adityagupta.arcompanion.databinding.ActivityMeaningBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException


class MeaningActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMeaningBinding
    lateinit var mediaPlayer: MediaPlayer


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

                    viewBinding.speaker.setOnClickListener {
                        playAudio(result.body()?.get(0)?.phonetics?.get(0)?.audio)
                    }
                    Log.i("some", result.body().toString())
                })


            }
        }


    }

    private fun playAudio(audio: String?) {
        val audioUrl = audio

        // initializing media player
        mediaPlayer = MediaPlayer()

        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(audioUrl)
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // below line is use to display a toast message.
        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()
    }


}