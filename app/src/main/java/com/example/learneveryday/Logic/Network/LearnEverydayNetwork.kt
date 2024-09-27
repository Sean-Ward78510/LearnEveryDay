package com.example.learneveryday.Logic.Network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-26 20:37
 * description
 */
object LearnEverydayNetwork {
    private val newsService = ServiceCreator.create<NewsService>()
    private val hotService = ServiceCreator.create<HotTopicService>()
    private val englishSentenceService = ServiceCreator.create<EnglishSentenceService>()
    suspend fun applyNews(num:Int = 20) = newsService.applyNews(num).await()
    suspend fun applyHotTopic() = hotService.applyHotTopic().await()
    suspend fun applyEnglishSentence() = englishSentenceService.applyEnglish().await()
    suspend fun applyWordTranslate(word : String) = englishSentenceService.applyWordTranslate(word).await()


    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    Log.d("Response", "applyNews4: " + body.toString())
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}