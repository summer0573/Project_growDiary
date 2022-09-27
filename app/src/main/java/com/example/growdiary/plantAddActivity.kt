package com.example.growdiary

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.growdiary.databinding.ActivityMainBinding
import com.example.growdiary.databinding.ActivityPlantAddBinding


class plantAddActivity : AppCompatActivity() {
    private val GET_GALLERY_IMAGE = 200
    private var imageview: ImageView? = null
    lateinit var binding: ActivityPlantAddBinding

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
        binding = ActivityPlantAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val itemList = listOf("다육이", "채소", "과일", "허브", "반려식물")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) Toast.makeText(
                    this@plantAddActivity,
                    itemList[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

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