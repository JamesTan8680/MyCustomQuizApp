package com.example.mycustomquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity() {

    private var mQuestionList: ArrayList<Quiz>? = null
    private lateinit var mDatabase: MyDataBase

    private lateinit var tv_question: TextView
    private lateinit var tv_option_one: TextView
    private lateinit var tv_option_two: TextView
    private lateinit var tv_option_three: TextView
    private lateinit var btn_submit: Button

    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        tv_question = findViewById(R.id.tv_question)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        btn_submit = findViewById(R.id.btn_submit)

        var i = 0
        mDatabase = MyDataBase(applicationContext)

        mQuestionList = mDatabase.getQuestions(this)
        count = mQuestionList!!.size
        Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()
        onClick(0)
        btn_submit.setOnClickListener {
            i++
            onClick(i)
        }
    }

    private fun onClick(item: Int) {
        if(item<count) {
            tv_question.text = mQuestionList!![item].questions
            tv_option_one.text = mQuestionList!![item].option_one
            tv_option_two.text = mQuestionList!![item].option_two
            tv_option_three.text = mQuestionList!![item].option_three
        }else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun onClick(item: Int){
//        Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()
//        if (item<=count)
//        {
//            tv_question.text=mQuestionList!![item].questions
//            tv_option_one.text=mQuestionList!![item].option_one
//            tv_option_two.text=mQuestionList!![item].option_two
//            tv_option_three.text=mQuestionList!![item].option_three
//        }else
//        {
//            var intent= Intent(this,QuizList::class.java)
//            startActivity(intent)
//        }
//    }


}