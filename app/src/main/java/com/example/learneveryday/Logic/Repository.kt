package com.example.learneveryday.Logic

import com.example.learneveryday.Logic.DataBase.AppDatabase
import com.example.learneveryday.Logic.Model.Response.EnglishSentence
import com.example.learneveryday.Logic.Model.Response.HotTopic
import com.example.learneveryday.Logic.Model.Response.News
import com.example.learneveryday.Logic.Model.Response.WordResult
import com.example.learneveryday.Logic.Network.LearnEverydayNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList


/**
 * @author 古娜拉·涤丑
 * @date 2024-07-26 21:00
 * description
 */
object Repository {

    suspend fun applyNews(num:Int = 20) = withContext(Dispatchers.IO) {
        val result = LearnEverydayNetwork.applyNews(num)
        if (result.code == "200") {
            result.result?.list
        } else {
            ArrayList<News>()
        }
    }

    suspend fun appHotTopic(): List<HotTopic>? {
        val result = LearnEverydayNetwork.applyHotTopic()
        if (result.code == "200"){
            return result.result?.list
        }else{
            return ArrayList<HotTopic>()
        }
    }
    suspend fun applyEnglish(): EnglishSentence? {
        var result = LearnEverydayNetwork.applyEnglishSentence()
        if (result.code == "200"){
            return result.result
        }else{
            return EnglishSentence(null,null,null)
        }
    }

    suspend fun applyWordTranslate(word : String) : WordResult? {
        var result = LearnEverydayNetwork.applyWordTranslate(word)
        if (result.code == "200"){
            return result.result
        }else{
            return WordResult(null,null)
        }
    }

//    suspend fun appHotTopic() = withContext(Dispatchers.IO){
//        val result = LearnEverydayNetwork.applyHotTopic()
//        if (result.code == "200"){
//            result.result?.list
//        }else{
//            ArrayList<HotTopic>()
//        }
//    }

}