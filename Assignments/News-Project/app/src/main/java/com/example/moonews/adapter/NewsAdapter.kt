package com.example.moonews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moonews.R
import com.example.moonews.databinding.NewsItemBinding
import com.example.moonews.model.NewsModel

class NewsAdapter(private var newsModel : ArrayList<NewsModel>, private var context : Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
    //TODO("Create a list of item from the model")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    //TODO - Implement data binding

      return  NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false))
//        val dataBinding : NewsItemBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(context),
//            NewsViewHolder(R.layout.news_item),
//            parent,
//            false
//        )
    }

    override fun getItemCount(): Int {
        return newsModel.size
    }

    override fun onBindViewHolder(holderNews: NewsViewHolder, position: Int) {

        var newsListItem = newsModel.get(position)

//        Glide.with(context).load(
//            "http://newsapi.org/v2/top-headlines?country=us&apiKey=4be5f6f296e242ae9c028912e77b8d8c&urlToImage="+ newsListItem.image)
//            .into(holderNews.image)
        holderNews.author.setText(newsListItem.author)
        holderNews.title.setText(newsListItem.title)
        holderNews.source.setText(newsListItem.source)
    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var author = itemView.findViewById<TextView>(R.id.txtViewAuthor)
        var title = itemView.findViewById<TextView>(R.id.txtViewTitle)
        var source = itemView.findViewById<TextView>(R.id.txtViewSource)
//        var image = itemView.findViewById<ImageView>(R.id.newsImage)

        override fun onClick(v: View) {
           TODO("Define view objects")
        }

    }

}
