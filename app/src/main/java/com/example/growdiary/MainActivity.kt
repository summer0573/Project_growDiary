package com.example.growdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val plusButton = findViewById<Button>(R.id.plus_Btn)
        plusButton.setOnClickListener {
            val intent = Intent(this, plantAddActivity::class.java)
            //intent.putExtra("userInput")
            startActivity(intent)
        }
    }
}