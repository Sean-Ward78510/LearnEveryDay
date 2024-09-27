package com.example.learneveryday.UI.ViewModel

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learneveryday.Logic.Dao.WordDao
import com.example.learneveryday.Logic.DataBase.AppDatabase
import com.example.learneveryday.Logic.Model.DataBaseEntity.Word
import com.example.learneveryday.Logic.Model.Response.EnglishSentence
import com.example.learneveryday.Logic.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-29 21:06
 * description
 */
class EnglishSentenceViewModel : ViewModel() {
    val _EnglishSentence = MutableLiveData<EnglishSentence>()
    val EnglishSentence : LiveData<EnglishSentence> get() = _EnglishSentence

    val _word = MutableLiveData<String>()
    val word : LiveData<String> get() = _word

    val _word_translate = MutableLiveData<String>()
    val word_translate : LiveData<String> get() = _word_translate

    var list = mutableListOf<Word>()
    val wordDao  by lazy { AppDatabase.getDatabase().wordDao() }



    fun applyEnglishSentence(){
        CoroutineScope(Dispatchers.IO).launch {
             val sentence = Repository.applyEnglish()
             sentence?.let {
                 _EnglishSentence.postValue(sentence)
             }
        }
    }

    fun applyWordTranslate(word : String){
        CoroutineScope(Dispatchers.IO).launch{
            val wordResult = Repository.applyWordTranslate(word)?.let {
                _word_translate.postValue(it.content)
                _word.postValue(it.word)
            }
        }
    }

    fun DealWithText(string: String , viewModel: EnglishSentenceViewModel) : SpannableString {
        val spannableString = SpannableString(string)
        val pattern = Pattern.compile("\\b\\w+\\b")
        val matcher = pattern.matcher(string)

        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            val word = matcher.group()

            val clickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    viewModel.applyWordTranslate(word)
                    Log.d("Word", "onClick: $word")
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.color = Color.BLACK
                }
            }
            spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        return spannableString
        // 确保父视图不拦截点击事件
        //binding.englishContent.parent.requestDisallowInterceptTouchEvent(true)
    }

    fun collectWord(word: Word){
        viewModelScope.launch(Dispatchers.IO){
            wordDao.insertWord(word)
            list.add(word)
        }
    }
    fun loadAllWord(){
        viewModelScope.launch(Dispatchers.IO) {
            list.addAll(wordDao.loadCollectWord())
        }
    }
    fun deleteWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("WordBook", "deleteWord: ")
            word.content?.let { wordDao.deleteWord(it) }
            list.remove(word)
        }
    }

}