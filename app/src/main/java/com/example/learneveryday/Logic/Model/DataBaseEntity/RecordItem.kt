package com.example.learneveryday.Logic.Model.DataBaseEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 11:24
 * description
 */
@Entity
data class RecordItem(var title : String, var source : String, var url : String, var pic_url : String, var time : String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}