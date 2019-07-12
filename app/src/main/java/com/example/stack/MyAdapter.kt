package com.example.stack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.raw_layout.view.*

class MyAdapter (private val quesList : ArrayList<ques>, private val listener : Listener) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(Ques : ques)
    }

    private val colors : Array<String> = arrayOf("#7E57C2", "#42A5F5", "#26C6DA", "#66BB6A", "#FFEE58", "#FF7043" , "#EC407A" , "#d32f2f")


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(quesList[position], listener, colors, position)

    }

//Check how many items you have to display//

    override fun getItemCount(): Int = quesList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.raw_layout, parent, false)
        return ViewHolder(view)

    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(Ques: ques, listener: Listener, colors : Array<String>, position: Int) {

            Log.d("Abhi tak sab badiya","5")


            itemView.setOnClickListener{ listener.onItemClick(Ques) }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
//            itemView.text_tags.text = Ques.tags
            itemView.text_is_answered.text = Ques.is_answered
            itemView.text_view_count.text = Ques.view_count
            itemView.text_answer_count.text=Ques.answer_count
            itemView.text_link.text=Ques.link
            itemView.text_title.text=Ques.title



        }

    }

}