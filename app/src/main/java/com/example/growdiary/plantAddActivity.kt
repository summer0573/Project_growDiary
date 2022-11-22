package com.example.growdiary


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.growdiary.databinding.ActivityMainBinding

import com.example.growdiary.databinding.ActivityPlantAddBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class plantAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlantAddBinding
    lateinit var getResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Firebase.firestore
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

        getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
                result ->
                    if(result.resultCode == RESULT_OK) {
                        binding.dateBtn.text = result.data?.getStringExtra("date")
                    }
        }

        binding.dateBtn.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            getResult.launch(intent)
        }

        binding.growBtn.setOnClickListener {
            val plantName = findViewById<EditText>(R.id.name_EditText).text.toString()
            val plantSpinner = findViewById<Spinner>(R.id.spinner).selectedItem.toString()
            val plantDate = findViewById<Button>(R.id.date_btn).text.toString()
            //날짜
            val plants = hashMapOf(
                "name" to plantName,
                "Spinner" to plantSpinner,
                "date" to plantDate
                //날짜
            )
            db.collection("plants")
                .document()
                .set(plants)
                .addOnSuccessListener {
                    Log.d("mytag", "DocumentSnapshot successfully written!")
                    Toast.makeText(applicationContext, "식물 정보 저장 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e -> Log.d("mytag", "Error writing document", e) }
        }
    }
}