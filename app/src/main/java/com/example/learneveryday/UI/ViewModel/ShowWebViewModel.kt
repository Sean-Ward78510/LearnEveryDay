package com.example.learneveryday.UI.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learneveryday.Logic.DataBase.AppDatabase
import com.example.learneveryday.Logic.Model.DataBaseEntity.RecordItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author 古娜拉·涤丑
 * @date 2024-08-01 09:55
 * description
 */
class ShowWebViewModel: ViewModel() {

    var recordItemDao = AppDatabase.getDatabase().recordItemDao()

    fun insertRecord(recordItem: RecordItem){
        viewModelScope.launch(Dispatchers.IO){
            recordItemDao.insertRecordItem(recordItem)
        }
    }
}