package com.example.learneveryday.Activity

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learneveryday.R
import com.example.learneveryday.UI.Tool.WordBookAdapter
import com.example.learneveryday.UI.ViewModel.WordBookViewModel
import com.example.learneveryday.databinding.ActivityShowWebViewBinding
import com.example.learneveryday.databinding.ActivityWordBookBinding
import com.yanzhenjie.recyclerview.OnItemMenuClickListener
import com.yanzhenjie.recyclerview.SwipeMenu
import com.yanzhenjie.recyclerview.SwipeMenuBridge
import com.yanzhenjie.recyclerview.SwipeMenuCreator
import com.yanzhenjie.recyclerview.SwipeMenuItem

class WordBookActivity() : AppCompatActivity() {

    lateinit var binding :ActivityWordBookBinding
    lateinit var viewModel: WordBookViewModel
    lateinit var wordBookAdapter : WordBookAdapter
    lateinit var manager : LinearLayoutManager
    lateinit var swipeMenuCreator: SwipeMenuCreator
    lateinit var swipeMenuClickListener : OnItemMenuClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidget()
    }

    fun initWidget(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_word_book)
        viewModel = ViewModelProvider(this).get(WordBookViewModel::class.java)

        wordBookAdapter = WordBookAdapter(this,viewModel.originWordList)
        manager = LinearLayoutManager(this)

        initSwipeMenu()

        binding.querySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(string: String): Boolean {
                viewModel.queryWord(string)
                return false
            }

        })

        viewModel.words.observe(this, Observer {
            wordBookAdapter.wordBookList = it
            wordBookAdapter.notifyDataSetChanged()
        })
        viewModel.loadAllWord()
    }
    fun initSwipeMenu(){
        swipeMenuCreator = object : SwipeMenuCreator{
            override fun onCreateMenu(leftMenu: SwipeMenu?, rightMenu: SwipeMenu?, position: Int) {
                var width = 128
                val height = ViewGroup.LayoutParams.MATCH_PARENT
                var deleteItem = SwipeMenuItem(this@WordBookActivity)
                                    .setImage(R.drawable.delete_icon)
                                    .setBackground(R.color.delete_background)
                                    .setWidth(width)
                                    .setHeight(height)
                var cancleItem = SwipeMenuItem(this@WordBookActivity)
                    .setImage(R.drawable.cancle_icon)
                    .setBackground(R.color.white)
                    .setWidth(width)
                    .setHeight(height)

                rightMenu?.addMenuItem(deleteItem)
                rightMenu?.addMenuItem(cancleItem)
            }

        }
        swipeMenuClickListener = object : OnItemMenuClickListener{
            override fun onItemClick(menuBridge: SwipeMenuBridge?, adapterPosition: Int) {
                menuBridge?.closeMenu()
                var select =  menuBridge?.position
                if (select == 0){
                    var word = viewModel._words.value?.get(adapterPosition)
                    word?.let { viewModel.deleteWord(it) }
                }
            }
        }
        binding.wordBookRecycle.apply {
            setSwipeMenuCreator(swipeMenuCreator)
            setOnItemMenuClickListener(swipeMenuClickListener)
            adapter = wordBookAdapter
            layoutManager = manager
        }
    }
}