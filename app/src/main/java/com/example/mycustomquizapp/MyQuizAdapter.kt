package com.example.mycustomquizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.quiz_layout.view.*

class MyQuizAdapter(val context: Context,private val questionList : ArrayList<Quiz>, private val listener:(Quiz)->Unit , private val listener1:(Quiz)->Unit): RecyclerView.Adapter<MyQuizAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.quiz_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = questionList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        private val questions = itemView.findViewById<TextView>(R.id.txt_questions)
        val btnUpdate = itemView.findViewById<Button>(R.id.btn_update)
        val btnDelete = itemView.findViewById<Button>(R.id.btn_delete)

         fun bind(item: Quiz){
            questions.text = item.questions
             itemView.btn_delete.setOnClickListener{
                 listener(item)
             }
             itemView.btn_update.setOnClickListener {
                 listener1(item)
             }
        }
    }


}