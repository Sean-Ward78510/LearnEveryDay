package com.example.learneveryday.Logic.Network

import com.example.learneveryday.LearnEveryday
import com.example.learneveryday.Logic.Model.Response.EnglishSentenceResponse
import com.example.learneveryday.Logic.Model.Response.WordResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-29 20:24
 * description
 */
interface EnglishSentenceService {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("everyday/index?key=${LearnEveryday.TOKEN}")
    fun applyEnglish() : Call<EnglishSentenceResponse>

    @GET("enwords/index?key=${LearnEveryday.TOKEN}")
    fun applyWordTranslate(@Query("word") word : String) : Call<WordResponce>
}