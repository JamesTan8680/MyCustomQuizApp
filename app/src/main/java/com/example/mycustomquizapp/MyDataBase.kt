package com.example.mycustomquizapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_quiz.*

class MyDataBase(context: Context) : SQLiteOpenHelper(context, "MyCustomApp", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(
            "CREATE TABLE QUIZ(QUIZID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTIONS TEXT NOT NULL, OPTION_ONE TEXT NOT NULL, " +
                    "OPTION_TWO TEXT NOT NULL, OPTION_THREE TEXT NOT NULL, CORRECT_ANSWER TEXT NOT NULL)"
        )
        db?.execSQL(
            "INSERT INTO QUIZ(QUESTIONS,OPTION_ONE,OPTION_TWO,OPTION_THREE,CORRECT_ANSWER) VALUES ('How old is Malaysia','53 years old', '55 years old', '70 years old'," +
                    "'53 years old')"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getQuestions(context: Context): ArrayList<Quiz> {
        val query = "SELECT * FROM QUIZ"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val quiz = ArrayList<Quiz>()

        if (cursor.count == 0)
            Toast.makeText(context, "No Records Found", Toast.LENGTH_SHORT).show()
        else {
            while (cursor.moveToNext()) {
                val collection: Quiz = Quiz()
                collection.id = cursor.getInt(0)
                collection.questions = cursor.getString(1)
                collection.option_one = cursor.getString(2)
                collection.option_two = cursor.getString(3)
                collection.option_three = cursor.getString(4)
                collection.correct_answer = cursor.getString(5)
                quiz.add(collection)
            }
            //Toast.makeText(context, quiz[0].questions, Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        return quiz
    }

    fun deleteQuestions(context: Context, id: Int): Boolean{
        val query = "DELETE FROM QUIZ WHERE QUIZID = $id"
        val db = this.writableDatabase
        var result: Boolean = false
        try{
            val cursor = db.execSQL(query) //run the query if everything is alright result = true
            result = true
        }catch (e: Exception){
            Log.e(ContentValues.TAG, "Error Deleting") //displaying log error deleting
        }
        return  result
    }

    fun updateQuestions(context: Context, quiz: Quiz){
        var cv = ContentValues()
        val db = this.writableDatabase
        cv.put("QUESTIONS",quiz.questions)
        cv.put("OPTION_ONE", quiz.option_one)
        cv.put("OPTION_TWO",quiz.option_two)
        cv.put("OPTION_THREE",quiz.option_three)
        cv.put("CORRECT_ANSWER",quiz.correct_answer)
        db.update("QUIZ",cv,"QUIZID=" + quiz.id.toString(),null)
        Toast.makeText(context, quiz.id.toString(), Toast.LENGTH_SHORT).show()

    }
}