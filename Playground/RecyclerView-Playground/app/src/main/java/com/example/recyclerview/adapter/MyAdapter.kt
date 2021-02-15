package com.example.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.ListItem

class MyAdapter(val context : Context, val allItems: ArrayList<ListItem>) : RecyclerView.Adapter<MyAdapter.ListItemHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        return ListItemHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return allItems.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        holder?.heading?.text = allItems.get(position).title
        holder?.description?.text = allItems.get(position).author
    }


    class ListItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

//        var heading: TextView = v.findViewById(R.id.heading)
//         var description: TextView = v.findViewById(R.id.description)

        var heading = v.findViewById<TextView>(R.id.heading)
        var description = v.findViewById<TextView>(R.id.description)


        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }


    }


}


