package com.example.learneveryday.UI.Tool

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learneveryday.Logic.Model.DataBaseEntity.Word
import com.example.learneveryday.databinding.ActivityShowWebViewBinding
import com.example.learneveryday.databinding.WordItemBinding

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 19:01
 * description
 */
class WordBookAdapter(var context : Context,var wordBookList: List<Word>)
    : RecyclerView.Adapter<WordBookAdapter.ViewHodle>() {
    class ViewHodle(var binding: WordItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodle {
        var binding = WordItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHodle(binding)
    }

    override fun getItemCount(): Int {
        return wordBookList.size
    }

    override fun onBindViewHolder(holder: ViewHodle, position: Int) {
        var word = wordBookList.get(position)
        holder.binding?.apply {
            wordItemContext.text = word.content
            wordItemTranslation.text = word.translate
        }
    }
}