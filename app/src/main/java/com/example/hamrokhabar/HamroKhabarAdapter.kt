package com.example.hamrokhabar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONObject

class HamroKhabarAdapter(private var listener: ItemClicked) : RecyclerView.Adapter<HamroKhabarVH>(){

    var items : ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HamroKhabarVH {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_layout,parent,false)
        val viewHolder = HamroKhabarVH(view)

        view.setOnClickListener {
            listener.itemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: HamroKhabarVH, position: Int) {

        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author


    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList (updatedList : ArrayList<News>) {

        items.clear()
        items.addAll(updatedList)

        // after updating the items.. we notify the adapters for the changes we have made
        notifyDataSetChanged()
        // this above function call will make the 3 implemented methods to run once again on updated adapter vals
    }

}
