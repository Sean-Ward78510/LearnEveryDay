package com.example.learneveryday.Logic.Model.Response


import com.google.gson.annotations.SerializedName

data class WordResponce(
    @SerializedName("code")
    val code: String?, // 200
    @SerializedName("msg")
    val msg: String?, // success
    @SerializedName("result")
    val result: WordResult?
)
data class WordResult(
    @SerializedName("content")
    val content: String?, // n:话,词,字,单词,言语,信,消息,诺言,歌词,词儿,命令,台词|v:说
    @SerializedName("word")
    val word: String? // word
)