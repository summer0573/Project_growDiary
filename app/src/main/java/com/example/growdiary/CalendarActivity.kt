package com.example.growdiary

import android.annotation.SuppressLint
import java.io.FileInputStream
import java.io.FileOutputStream

import android.view.View
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CalendarActivity : AppCompatActivity() {
    var userID: String = "userID"
    lateinit var calendarView: CalendarView
    lateinit var diaryTextView: TextView
    lateinit var contextEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        // UI값 생성
        calendarView=findViewById(R.id.calendarView)
        diaryTextView=findViewById(R.id.diaryTextView)


        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            contextEditText.setText("")
//            checkDay(year, month, dayOfMonth, userID)
        }

    }




}