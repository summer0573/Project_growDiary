package com.example.growdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class plantAddFirebase : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_add)
        auth = Firebase.auth

        val db = Firebase.firestore

        var growBtn = findViewById<Button>(R.id.grow_btn)
        var dateBtn = findViewById<Button>(R.id.date_btn)

        growBtn.setOnClickListener {
            val plantImage = findViewById<ImageView>(R.id.imageView)
            val plantName = findViewById<EditText>(R.id.name_EditText).text.toString()
            val plantSpinner = findViewById<Spinner>(R.id.spinner)
            val plantDate = dateBtn.text.toString()
            //날짜
            val plants = hashMapOf(
                "image" to plantImage,
                "name" to plantName,
                "Spinner" to plantSpinner,
                "date" to plantDate
                //날짜
            )
            db.collection("plants").document("trees")
                .set(plants)
                .addOnSuccessListener {
                    Log.d("mytag", "DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e -> Log.w("mytag", "Error writing document", e) }


        }


    }
}