package com.example.mycustomquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class QuizList : AppCompatActivity() {

    var quizobj: Quiz = Quiz()

    private lateinit var mDatabase: MyDataBase
    private lateinit var questionslist: RecyclerView
    private lateinit var adapter: MyQuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_list)

        val list = ArrayList<Quiz>()

        questionslist = findViewById(R.id.questionsList)

        mDatabase = MyDataBase(applicationContext)
        var quiz = mDatabase.getQuestions(this)
        //Toast.makeText(this, quiz[0].id, Toast.LENGTH_SHORT).show()
        adapter = MyQuizAdapter(
            this,
            quiz,
            listener = { showquestion(it) },
            listener1 = { editactivity(it) })
        view()
    }


    fun showquestion(list: Quiz) {
        //Toast.makeText(this, list.id.toString(), Toast.LENGTH_SHORT).show()
        mDatabase.deleteQuestions(this, list.id)
        finish()
        startActivity(intent)
    }

    fun editactivity(list: Quiz) {
//        var i=Intent(this, UpdateQuestionActivity::class.java).apply {
//            putExtra("id", list.id.toString())
//        }
//        startActivity(i)
        //Toast.makeText(this, list.id.toString(), Toast.LENGTH_SHORT).show()
        showDialog(list)
    }



    fun showDialog(list: Quiz) {
        var quiz: Quiz = list
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.button)

        var setup_question = dialog.findViewById<EditText>(R.id.setup_question)
        var fake_answer1 = dialog.findViewById<EditText>(R.id.fake_answer1)
        var fake_answer2 = dialog.findViewById<EditText>(R.id.fake_answer2)
        var fake_answer3 = dialog.findViewById<EditText>(R.id.fake_answer3)
        var correct_answer = dialog.findViewById<EditText>(R.id.correct_answer)
        var btnUpload = dialog.findViewById<ImageView>(R.id.btnUpload)

        setup_question?.setText(list.questions)
        fake_answer1?.setText(list.option_one)
        fake_answer2?.setText(list.option_two)
        fake_answer3?.setText(list.option_three)
        correct_answer?.setText(list.correct_answer)
//        Toast.makeText(this, list.id.toString(), Toast.LENGTH_SHORT).show()

        btnUpload?.setOnClickListener {
            quiz.questions = setup_question?.text.toString()
            quiz.option_one = fake_answer1?.text.toString()
            quiz.option_two = fake_answer2?.text.toString()
            quiz.option_three = fake_answer3?.text.toString()
            quiz.correct_answer = correct_answer?.text.toString()
//            Toast.makeText(this, quiz.id.toString(), Toast.LENGTH_SHORT).show()
            mDatabase.updateQuestions(this, quiz)
            //Toast.makeText(this, quiz.questions, Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }
        dialog.show()
    }

    private fun view() {
        questionslist.layoutManager = LinearLayoutManager(this)
        questionslist.adapter = adapter
    }

}