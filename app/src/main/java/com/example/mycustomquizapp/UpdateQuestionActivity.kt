package com.example.mycustomquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class UpdateQuestionActivity : AppCompatActivity() {

    private lateinit var btnUpload: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button)


        btnUpload = findViewById(R.id.btnUpload)
        var i = intent.getStringExtra("id")
        btnUpload.text = i.toString()
    }
}