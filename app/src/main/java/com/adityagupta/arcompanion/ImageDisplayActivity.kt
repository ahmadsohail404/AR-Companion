package com.adityagupta.arcompanion

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adityagupta.arcompanion.databinding.ActivityImageDisplayBinding
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText


class ImageDisplayActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityImageDisplayBinding


    lateinit var image: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        viewBinding = ActivityImageDisplayBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var st = intent.getStringExtra("path")!!
        var t = Uri.parse(st)
        image = MediaStore.Images.Media.getBitmap(contentResolver, t)

        image = rotateImage(image, 90.0f)
        viewBinding.imageView.setImageBitmap(image)

        val image = FirebaseVisionImage.fromBitmap(image)

        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

        detector.processImage(image)
            .addOnSuccessListener { firebaseVisionText ->
                processResultText(firebaseVisionText)
            }
            .addOnFailureListener {
                Log.i("something", "failed")
            }

        viewBinding.selectedWord.setOnClickListener {
            var query ="https://www.dictionary.com/browse/" +  viewBinding.selectedWord.text.toString().lowercase()
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(query)
                )
            )
        }
    }


    private fun processResultText(resultText: FirebaseVisionText) {
        if (resultText.textBlocks.size == 0) {
            Log.i("something", "no text found")
            return
        }
        var str = ""
        for (block in resultText.textBlocks) {
            val blockText = block.text
            Log.i("bounding", block.text)
            str += block.text
            str += " "
            Log.i("bounding", block.boundingBox.toString())

        }
        viewBinding.selectedWord.text = str

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
