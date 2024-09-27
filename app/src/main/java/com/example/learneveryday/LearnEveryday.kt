package com.example.learneveryday

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-26 19:48
 * description
 */
class LearnEveryday : Application() {
    companion object{
        @SuppressLint()
        lateinit var context : Context
        const val TOKEN = "7e3d2a1e6a8126effd41726656523d71"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}