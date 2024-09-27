package com.example.learneveryday.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learneveryday.Logic.DataBase.AppDatabase
import com.example.learneveryday.Logic.Model.DataBaseEntity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 15:19
 * description
 */
class WordBookViewModel : ViewModel() {
    val _words = MutableLiveData<List<Word>>()
    val words : LiveData<List<Word>> get() = _words
    val wordDao = AppDatabase.getDatabase().wordDao()
    var originWordList : MutableList<Word> =  mutableListOf()

    fun deleteWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            word.content?.let { wordDao.deleteWord(it) }
            originWordList.remove(word)
            _words.postValue(originWordList)
        }
    }
    fun loadAllWord(){
        viewModelScope.launch(Dispatchers.IO) {
            var list = wordDao.loadCollectWord()
            originWordList.addAll(list)
            _words.postValue(list)
        }
    }
    fun queryWord(string: String){
        if(!string.trim().isEmpty()){
            viewModelScope.launch(Dispatchers.IO) {
                _words.postValue(wordDao.queryWord(string))
            }
        }else{
            _words.value = originWordList
        }
    }
}