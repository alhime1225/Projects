package com.example.moonews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moonews.R
import com.example.moonews.adapter.NewsAdapter
import com.example.moonews.databinding.NewsItemBinding
import com.example.moonews.model.NewsModel

class MainActivity : AppCompatActivity() {
    //TODO : Get the recycler view and the adapter
    lateinit var recyclerView : RecyclerView
    val newsListItem : ArrayList<NewsModel> = ArrayList()
//    private val viewModel by lazy { ViewModelProviders.of(this).get(NewsModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var item1 = NewsModel("TMX", "Test", "Anonymous")
        newsListItem.add(item1)

//        val binding : NewsItemBinding = DataBindingUtil.setContentView(this, R.layout.news_item)
//        binding.lifecycleOwner = this

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = NewsAdapter(newsListItem, this)


    }
}