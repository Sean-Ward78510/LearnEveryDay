package com.example.learneveryday.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learneveryday.Logic.DataBase.AppDatabase
import com.example.learneveryday.Logic.Model.DataBaseEntity.RecordItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 21:53
 * description
 */
class RecordItemViewModel : ViewModel() {
    val _recordList  = MutableLiveData<List<RecordItem>>()
    val recordList : LiveData<List<RecordItem>> get() = _recordList
    var recordItemDao = AppDatabase.getDatabase().recordItemDao()
    var originList = mutableListOf<RecordItem>()

    fun loadAllRecord(){
        viewModelScope.launch(Dispatchers.IO){
            var list = recordItemDao.loadAllRecordItem()
            originList.addAll(list)
            _recordList.postValue(list)

        }
    }

    fun deleteRecord(recordItem: RecordItem){
        viewModelScope.launch(Dispatchers.IO){
            recordItemDao.deleteRecordItemById(recordItem.id)
            originList.remove(recordItem)
            _recordList.postValue(originList)
        }
    }
    fun queryRecordItem(string: String){
        if (!string.trim().isEmpty()){
            viewModelScope.launch(Dispatchers.IO){
                _recordList.postValue(recordItemDao.queryRecordItem(string))
            }
        }else{
            _recordList.value = originList
        }
    }
}