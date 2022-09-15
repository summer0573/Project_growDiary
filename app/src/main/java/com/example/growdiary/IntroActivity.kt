package com.example.growdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.growdiary.MainActivity
import com.example.growdiary.R
import android.os.Bundle
import android.os.Handler

class IntroActivity: AppCompatActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
           setContentView(R.layout.intro_layout)
           var handler = Handler()
           handler.postDelayed({
           var intent = Intent(this, MainActivity::class.java)
           startActivity(intent)
          }, 1000)  }
       override fun onPause(){
          super.onPause()
          finish()
       }
}