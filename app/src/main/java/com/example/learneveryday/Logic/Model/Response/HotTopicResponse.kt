package com.example.learneveryday.Logic.Model.Response


import com.google.gson.annotations.SerializedName

data class HotTopicResponse(
    @SerializedName("code")
    val code: String?, // 200
    @SerializedName("msg")
    val msg: String?, // success
    @SerializedName("result")
    val result: HotTopicResult?
)
    data class HotTopicResult(
        @SerializedName("list")
        val list: List<HotTopic>?
    )
    data class HotTopic(
        @SerializedName("hotindex")
        val hotindex: Int?, // 20666142
        @SerializedName("word")
        val word: String? // 张雨霏：这次奥运会比想象中压力大
    )