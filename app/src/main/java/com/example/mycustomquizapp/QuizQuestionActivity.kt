package com.example.mycustomquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity() {


    private var mQuestionList: ArrayList<Quiz>? = null
    private lateinit var mDatabase: MyDataBase
    private var mSelectedOptionPosition:Int = 0
    private var setOptionValue: String=""
    private var mCurrentPosition:Int = 1
    private var mCorrectAnswers: Int=0
    private var mUserName: String?= null



    private lateinit var tv_question: TextView
    private lateinit var tv_option_one: TextView
    private lateinit var tv_option_two: TextView
    private lateinit var tv_option_three: TextView
    private lateinit var btn_submit: Button

    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        tv_question = findViewById(R.id.tv_question)
        tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        btn_submit = findViewById(R.id.btn_submit)

        tv_option_one.setOnClickListener {
            selectedOptionView(tv_option_one, 1)
            setOptionValue = tv_option_one.text.toString()
        }
        tv_option_two.setOnClickListener {
            selectedOptionView(tv_option_two, 2)
            setOptionValue = tv_option_two.text.toString()
        }
        tv_option_three.setOnClickListener {
            selectedOptionView(tv_option_three, 3)
            setOptionValue = tv_option_three.text.toString()

        }

        mDatabase = MyDataBase(applicationContext)

        mQuestionList = mDatabase.getQuestions(this)
        count = mQuestionList!!.size
        Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()
        var i = 0
        onClick(0)
        btn_submit.setOnClickListener {
            if (btn_submit.text == "Submit") {

                if(mQuestionList!!.size == 1) {

                    ansView(mSelectedOptionPosition, R.drawable.wrong_option_bg)

                    when (mQuestionList!![i].correct_answer) {
                        mQuestionList!![i].option_one -> ansView(1, R.drawable.correct_option_bg)
                        mQuestionList!![i].option_two -> ansView(2, R.drawable.correct_option_bg)
                        mQuestionList!![i].option_three -> ansView(3, R.drawable.correct_option_bg)
                    }
                }

                if(mCurrentPosition == mQuestionList!!.size){
                    btn_submit.text = "FINISH"
                }else{
                    if(mQuestionList!![i].correct_answer != setOptionValue){
                        ansView(mSelectedOptionPosition, R.drawable.wrong_option_bg)

                        when(mQuestionList!![i].correct_answer){
                            mQuestionList!![i].option_one -> ansView(1, R.drawable.correct_option_bg)
                            mQuestionList!![i].option_two -> ansView(2, R.drawable.correct_option_bg)
                            mQuestionList!![i].option_three -> ansView(3, R.drawable.correct_option_bg)
                        }

                    }else{
                        mCorrectAnswers++
                        ansView(mSelectedOptionPosition, R.drawable.correct_option_bg)
                    }
                    btn_submit.text = "GO TO NEXT QUESTION"
                }
            } else {
                mSelectedOptionPosition = 0
                btn_submit.text = "Submit"
                i++
                onClick(i)
                defaultOptionsView()
            }

        }
    }

    private fun onClick(item: Int) {
        if(item<count) {
            println(mQuestionList?.get(item))
            tv_question.text = mQuestionList!![item].questions
            tv_option_one.text = mQuestionList!![item].option_one
            tv_option_two.text = mQuestionList!![item].option_two
            tv_option_three.text = mQuestionList!![item].option_three
        }else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(Constants.USER_NAME, mUserName)
            intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswers)
            startActivity(intent)
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun ansView (answer: Int, drawableView: Int){
        when(answer){
            1->{
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2->{
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3->{
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int){

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#FF6200EE"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.default_option_border_bg
        )
    }


}