package com.example.learneveryday.UI.Tool

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learneveryday.Activity.ShowWebViewActivity
import com.example.learneveryday.Logic.Model.DataBaseEntity.RecordItem
import com.example.learneveryday.Util.loadImage
import com.example.learneveryday.databinding.NewsItemBinding

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 22:22
 * description
 */
class RecordItemAdapter(var context: Context, var recordList : List<RecordItem>) :
    RecyclerView.Adapter<RecordItemAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        var holder = ViewHolder(binding)
        return holder
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var news = recordList.get(position)
        holder.binding.apply {
            news.pic_url?.let { newsPic.loadImage(it) }
            newsTitle.text = news.title
            newsSource.text = news.source
            newsTime.text = news.time
            holder.itemView.setOnClickListener {
                var intent = Intent(context, ShowWebViewActivity::class.java).apply {
                    putExtra("url",news.url)
                    putExtra("pic_url",news.pic_url)
                    putExtra("title",news.title)
                    putExtra("source",news.source)
                }
                context.startActivity(intent)
            }
        }
    }
}