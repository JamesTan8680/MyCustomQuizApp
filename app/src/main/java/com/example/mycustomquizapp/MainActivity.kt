package com.example.mycustomquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_add = findViewById<FloatingActionButton>(R.id.btn_add)
        val btn_listRecord = findViewById<FloatingActionButton>(R.id.btn_listRecord)
        val btn_start=findViewById<Button>(R.id.btn_start)

        btn_add.setOnClickListener {
            val intent = Intent(this, CreateQuizActivity::class.java)
            startActivity(intent)
        }

        btn_listRecord.setOnClickListener {
            val intent = Intent(this, QuizList::class.java)
            startActivity(intent)
        }

        btn_start.setOnClickListener {
            val intent = Intent(this, QuizQuestionActivity::class.java)
            startActivity(intent)
        }

    }
}