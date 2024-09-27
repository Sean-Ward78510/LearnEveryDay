package com.example.learneveryday.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learneveryday.R
import com.example.learneveryday.UI.Tool.NewsAdapter
import com.example.learneveryday.UI.Tool.RecordItemAdapter
import com.example.learneveryday.UI.ViewModel.RecordItemViewModel
import com.example.learneveryday.databinding.ActivityNewsHistoryRecordBinding
import com.yanzhenjie.recyclerview.OnItemMenuClickListener
import com.yanzhenjie.recyclerview.SwipeMenu
import com.yanzhenjie.recyclerview.SwipeMenuBridge
import com.yanzhenjie.recyclerview.SwipeMenuCreator
import com.yanzhenjie.recyclerview.SwipeMenuItem

class NewsHistoryRecordActivity : AppCompatActivity() {

    lateinit var binding : ActivityNewsHistoryRecordBinding
    lateinit var viewModel : RecordItemViewModel
    lateinit var recordAdapter: RecordItemAdapter
    lateinit var manager : LinearLayoutManager
    lateinit var swipeMenuCreator: SwipeMenuCreator
    lateinit var swipeMenuClickListener : OnItemMenuClickListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidget()
    }

    fun initWidget(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news_history_record)
        viewModel = ViewModelProvider(this).get(RecordItemViewModel::class.java)

        recordAdapter = RecordItemAdapter(this,viewModel.originList)
        manager = LinearLayoutManager(this)

        initSwipeMenu()

        binding.searchRecord.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(content: String?): Boolean {
                content?.let { viewModel.queryRecordItem(it) }
                return false
            }

        })

        viewModel.recordList.observe(this, Observer {
            recordAdapter.apply {
                recordList = it
                notifyDataSetChanged()
            }
        })
        viewModel.loadAllRecord()
    }

    fun initSwipeMenu(){
        swipeMenuCreator = object : SwipeMenuCreator {
            override fun onCreateMenu(leftMenu: SwipeMenu?, rightMenu: SwipeMenu?, position: Int) {
                var width = 128
                val height = ViewGroup.LayoutParams.MATCH_PARENT
                var deleteItem = SwipeMenuItem(this@NewsHistoryRecordActivity)
                    .setImage(R.drawable.delete_icon)
                    .setBackground(R.color.delete_background)
                    .setWidth(width)
                    .setHeight(height)
                var cancleItem = SwipeMenuItem(this@NewsHistoryRecordActivity)
                    .setImage(R.drawable.cancle_icon)
                    .setBackground(R.color.white)
                    .setWidth(width)
                    .setHeight(height)

                rightMenu?.addMenuItem(deleteItem)
                rightMenu?.addMenuItem(cancleItem)
            }

        }
        swipeMenuClickListener = object : OnItemMenuClickListener {
            override fun onItemClick(menuBridge: SwipeMenuBridge?, adapterPosition: Int) {
                menuBridge?.closeMenu()
                var select =  menuBridge?.position
                if (select == 0){
                    var record = viewModel._recordList.value?.get(adapterPosition)
                    record?.let { viewModel.deleteRecord(it) }
                }
            }
        }
        binding.recordRecycle.apply {
            setSwipeMenuCreator(swipeMenuCreator)
            setOnItemMenuClickListener(swipeMenuClickListener)
            adapter = recordAdapter
            layoutManager = manager
        }
    }
}