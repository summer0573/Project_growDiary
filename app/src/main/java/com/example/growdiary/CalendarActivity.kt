package com.example.growdiary

import android.annotation.SuppressLint
import android.content.Intent
import java.io.FileInputStream
import java.io.FileOutputStream

import android.view.View
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CalendarActivity : AppCompatActivity() {
    var userID: String = "userID"
    lateinit var calendarView: CalendarView
    lateinit var diaryTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        // UI값 생성
        calendarView = findViewById(R.id.calendarView)
        diaryTextView = findViewById(R.id.diaryTextView)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            val dataIntent = Intent(this, plantAddActivity::class.java)
            dataIntent.putExtra("date", date)
//            val dataIntent = Intent(this, plantAddActivity::class.java).apply {
//                putExtra("date", date)
//            }
            setResult(RESULT_OK, dataIntent)
            finish()
            // diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            // checkDay(year, month, dayOfMonth, userID)
        }
    }
}