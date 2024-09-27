package com.example.learneveryday.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learneveryday.Logic.Model.Response.HotTopic
import com.example.learneveryday.Logic.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-29 15:19
 * description
 */
class HotTopicViewModel : ViewModel() {
    val _hotLiveData = MutableLiveData<List<HotTopic>>()
    val hotLiveData : LiveData<List<HotTopic>> get() = _hotLiveData

    fun applyHotTopic(){
        CoroutineScope(Dispatchers.IO).launch {
            var hotList = Repository.appHotTopic()
            hotList?.let {
                _hotLiveData.postValue(hotList)
            }
        }
    }
}