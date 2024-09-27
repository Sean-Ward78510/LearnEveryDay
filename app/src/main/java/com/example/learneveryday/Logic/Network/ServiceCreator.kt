package com.example.learneveryday.Logic.Network

import android.os.Parcelable.Creator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-26 20:29
 * description
 */
object ServiceCreator {
    private const val BASE_URL = "https://apis.tianapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>) : T = retrofit.create(serviceClass)
    inline  fun <reified T> create() : T = create((T::class.java))
}