package com.example.learneveryday.Logic.Network

import com.example.learneveryday.LearnEveryday
import com.example.learneveryday.Logic.Model.Response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-26 20:13
 * description
 */
interface NewsService {
    @GET("keji/index?key=${LearnEveryday.TOKEN}")
    fun applyNews(@Query("num") num :Int = 20) : Call<NewsResponse>
}