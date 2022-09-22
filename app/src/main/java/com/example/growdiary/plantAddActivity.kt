package com.example.growdiary

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class plantAddActivity : AppCompatActivity() {
    private val GET_GALLERY_IMAGE = 200
    private var imageview: ImageView? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_add)
        imageview = findViewById<View>(R.id.imageView) as ImageView
        imageview!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri = data.data
            imageview!!.setImageURI(selectedImageUri)
        }
    }
}