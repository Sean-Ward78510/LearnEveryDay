package com.example.learneveryday.UI.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learneveryday.Logic.Model.Response.News
import com.example.learneveryday.Logic.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * @author 古娜拉·涤丑
 * @date 2024-07-27 10:52
 * description
 */
class NewsViewModel : ViewModel(){
    val _newsLiveData = MutableLiveData<List<News>>()
    val newsLiveData: LiveData<List<News>> get() = _newsLiveData
    val newsList = ArrayList<News>()
//    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun applyNews(num: Int = 20) {
//        viewModelScope.launch(Dispatchers.IO) {  }
        CoroutineScope(Dispatchers.IO).launch {
            var newsList = Repository.applyNews(num)
            newsList?.let {
                _newsLiveData.postValue(newsList)
                Log.d("News", "applyNews3: ")
            }
        }
    }
}