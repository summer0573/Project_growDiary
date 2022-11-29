package com.example.growdiary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growdiary.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding    // 뷰 바인딩
    val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언
    val itemList = arrayListOf<ListLayout>()    // 리스트 아이템 배열
    val adapter = ListAdapter(itemList)         // 리사이클러 뷰 어댑터


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val itemList: ArrayList<ListLayout> = ArrayList()
        db.collection("plants")   // 작업할 컬렉션
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우

                for (document in result) {  // 가져온 문서들은 result에 들어감
                    val item =
                        ListLayout(
                            document["name"] as String,
                            document["Spinner"] as String,
                            document["date"] as String
                        )
                    itemList.add(item)
                }
                Log.d("mytag", itemList.toString())
                // adapter.notifyDataSetChanged()  // 리사이클러 뷰 갱신
                binding.rvList.layoutManager = LinearLayoutManager(this)
                binding.rvList.adapter = ListAdapter(itemList)

            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }



        val plusButton = findViewById<FloatingActionButton>(R.id.plus_Btn)
        plusButton.setOnClickListener {
            Log.d("mytag", "plus button")
            val intent = Intent(this, plantAddActivity::class.java)
            startActivity(intent)
        }



    }
}