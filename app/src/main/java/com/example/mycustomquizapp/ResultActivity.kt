package com.example.mycustomquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var quiz: Quiz = Quiz()
    private lateinit var mDatabase: MyDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mDatabase = MyDataBase(applicationContext)

        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text = username

        var noQuiz = mDatabase.numberofQuestions()
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWER, 0)

        tv_score.text = "Your score is $correctAnswer out of $noQuiz"

        btn_finish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}