package com.example.learneveryday.Logic.Model.Response


import com.google.gson.annotations.SerializedName

data class EnglishSentenceResponse(
    @SerializedName("code")
    val code: String?, // 200
    @SerializedName("msg")
    val msg: String?, // success
    @SerializedName("result")
    val result: EnglishSentence?
)
data class EnglishSentence(
    @SerializedName("content")
    val content: String?, // Positive thinking will let you do everything better than negative thinking will.
    @SerializedName("note")
    val note: String?, // 比起悲观，乐观会让你把每一件事都做得更好。
    @SerializedName("source")
    val source: String?
)