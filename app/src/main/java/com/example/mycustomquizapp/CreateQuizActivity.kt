package com.example.mycustomquizapp

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_quiz.*

class CreateQuizActivity : AppCompatActivity(){
    lateinit var btn_submit_question: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_quiz)

        btn_submit_question =  findViewById<Button>(R.id.btn_submit_question)

        var helper = MyDataBase(applicationContext)
        var db = helper.readableDatabase
        var rs= db.rawQuery("SELECT * FROM QUIZ",null)

        if(rs.moveToNext())
            Toast.makeText(applicationContext,rs.getString(1), Toast.LENGTH_LONG).show()

        btn_submit_question.setOnClickListener {
            var cv = ContentValues()
            cv.put("QUESTIONS",setup_question.text.toString())
            cv.put("OPTION_ONE", fake_answer1.text.toString())
            cv.put("OPTION_TWO",fake_answer2.text.toString())
            cv.put("OPTION_THREE",fake_answer3.text.toString())
            cv.put("CORRECT_ANSWER",correct_answer.text.toString())
            db.insert("QUIZ",null,cv)

            setup_question.setText("")
            fake_answer1.setText("")
            fake_answer2.setText("")
            fake_answer3.setText("")
            correct_answer.setText("")
            setup_question.requestFocus()
        }



    }
}