package com.example.growdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val plusButton = findViewById<Button>(R.id.plus_Btn)
        plusButton.setOnClickListener {
            Log.d("mytag", "plus button")
            val intent = Intent(this, plantAddActivity::class.java)
            startActivity(intent)
        }
    }
}