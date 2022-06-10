package com.adityagupta.arcompanion

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Rect
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
    var words = mutableListOf<String>()
    var coords = mutableListOf<Rect>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        viewBinding = ActivityImageDisplayBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val st = intent.getStringExtra("path")!!
        val t = Uri.parse(st)
        image = MediaStore.Images.Media.getBitmap(contentResolver, t)


        image = rotateImage(image, 90.0f)

        viewBinding.cameraImage.viewTreeObserver.addOnGlobalLayoutListener {
            image = Bitmap.createScaledBitmap(image, viewBinding.cameraImage.getWidth(), viewBinding.cameraImage.getHeight(), true)

            viewBinding.cameraImage.setImageBitmap(image)

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

        viewBinding.cameraImage.setOnTouchListener { view, motionEvent ->
            var x = motionEvent.x
            var y = motionEvent.y
            var index = 0
            for (i in coords){
                if(x > i.left && x < i.right){
                    if(y > i.top && y < i.bottom){
                        viewBinding.selectedWord.text = words[index]
                        break
                    }
                }
                index++
            }



            true
        }



    }




    @SuppressLint("ClickableViewAccessibility")
    private fun processResultText(resultText: FirebaseVisionText) {
        if (resultText.textBlocks.size == 0) {
            Log.i("something", "no text found")
            return
        }
        var str = ""

        for (block in resultText.textBlocks) {
            val blockText = block.text
            val blockCornerPoints = block.cornerPoints
            val blockFrame = block.boundingBox
            for (line in block.lines) {
                val lineText = line.text
                val lineCornerPoints = line.cornerPoints
                val lineFrame = line.boundingBox
                for (element in line.elements) {
                    val elementText = element.text
                    val elementCornerPoints = element.cornerPoints
                    val elementFrame = element.boundingBox
                    str += elementText
                    str += " "
                    words.add(elementText)
                    if (elementFrame != null) {
                        coords.add(elementFrame)
                    }

                }
            }
        }

        Log.i("wordlist", coords.toString())


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

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }




}

