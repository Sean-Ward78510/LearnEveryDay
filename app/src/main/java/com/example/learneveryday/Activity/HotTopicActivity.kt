package com.example.learneveryday.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learneveryday.R
import com.example.learneveryday.UI.ViewModel.HotTopicViewModel
import com.example.learneveryday.databinding.ActivityHotTopicBinding
import com.example.learneveryday.databinding.HotItemBinding

class HotTopicActivity : AppCompatActivity() {
    lateinit var binding : ActivityHotTopicBinding
    lateinit var viewModel: HotTopicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidget()
    }

    fun initWidget(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_hot_topic)
        viewModel = ViewModelProvider(this).get(HotTopicViewModel::class.java)

        viewModel.hotLiveData.observe(this, Observer {
            it?.let {
                binding.hotLinearlayout.removeAllViews()
                var list = it.sortedByDescending { it.hotindex }
                for (i in 0..list.size - 1){
                    var hotTopic = list.get(i)

                    var hotBinding = DataBindingUtil.inflate<HotItemBinding>(
                        layoutInflater,R.layout.hot_item,
                        binding.hotLinearlayout,true)

                    hotBinding?.apply {
                        sequence.text = "${i + 1}"
                        hotTitle.text = hotTopic.word
                        hotValue.text = hotTopic.hotindex.toString()
                    }
                    //binding.hotLinearlayout.addView(hotBinding.root)
                }
            }
        })
        viewModel.applyHotTopic()
    }
}