package com.adityagupta.arcompanion

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adityagupta.arcompanion.databinding.ActivityImageDisplayBinding


class ImageDisplayActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityImageDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        viewBinding = ActivityImageDisplayBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var st = intent.getStringExtra("path")!!
        var t = Uri.parse(st)
        var z = MediaStore.Images.Media.getBitmap(contentResolver, t)

        z = rotateImage(z, 90.0f)
        viewBinding.imageView.setImageBitmap(z)
    }

    fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        var matrix: Matrix = Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(
            source,
            0,
            0,
            source.getWidth(),
            source.getHeight(),
            matrix,
            true
        );
    }

}