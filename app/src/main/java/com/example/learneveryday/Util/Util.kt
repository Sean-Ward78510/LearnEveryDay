package com.example.learneveryday.Util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.learneveryday.R
import com.example.learneveryday.R.drawable

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-27 10:19
 * description
 */
fun ImageView.loadImage(url:String){
    Glide.with(this)
        .load(url)
        .error(R.drawable.default_pic)
        .into(this)
}