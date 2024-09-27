package com.example.learneveryday.Logic.Network

import com.example.learneveryday.LearnEveryday
import com.example.learneveryday.Logic.Model.Response.HotTopicResponse
import retrofit2.http.GET

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-29 15:05
 * description
 */
interface HotTopicService {
    @GET("toutiaohot/index?key=${LearnEveryday.TOKEN}")
    fun applyHotTopic() : retrofit2.Call<HotTopicResponse>
}