package com.sohail.arcompanion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adityagupta.arcompanion.databinding.ActivityMainBinding
//import com.sohail.arcompanion.databinding.ActivityMainBinding
import java.util.*

typealias LumaListener = (luma: Double) -> Unit


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.button2.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java).apply {
            }
            startActivity(intent)
        }
        // Request camera permissions

    }

}