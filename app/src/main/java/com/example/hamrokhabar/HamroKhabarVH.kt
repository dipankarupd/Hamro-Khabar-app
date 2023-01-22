package com.example.hamrokhabar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HamroKhabarVH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var title : TextView = itemView.findViewById(R.id.title)

    var author : TextView = itemView.findViewById(R.id.newsAuthor)
}