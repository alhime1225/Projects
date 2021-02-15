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

//TODO 2 : Create a custom adapter which will inherit from the RecyclerView.Adapter
//TODO 4 : Get the data from the model
//Getting the data by defining a list, the way achieved it is by creating a constructor which initializes the data
class MyViewAdapter (var listItems : ArrayList<ListItem>, var context : Context) : RecyclerView.Adapter<MyViewAdapter.ViewHolder>() {

    //TODO 4 : Implement the onCreateViewHolder which holds the inflater to inflate the view and to return the inflated view
    //Will be called whenever the view holder is created. Instance of ViewHolder class is then created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Create a view object
        val view =  LayoutInflater.from(context)
                //Inflating the layout which holds the items
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }


    //This method will bind the actual data to the viewholder (ViewHolder class). Which means, this will show the actual data to the recycler view
    override fun getItemCount(): Int {
        //TODO 5 : Return the elements from the list
        return listItems.size
    }

    //TODO 6 : Get the current item from the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Getting individual list from the current position
        var listItem = listItems.get(position)

        holder.txtViewHead.setText(listItem.title)
        holder.txtViewDesc.setText(listItem.author)
    }

    //TODO 3 : Create an innerclass which will be the view holder
    //This is where you're going to define the view objects (e.g. TextView)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var txtViewHead = itemView.findViewById<TextView>(R.id.heading)
        var txtViewDesc = itemView.findViewById<TextView>(R.id.description)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
//            Toast.makeText(this,"Clicked " , 2).show()
            Log.d("MyRecyclerView", "CLICKED")
        }

    }

}