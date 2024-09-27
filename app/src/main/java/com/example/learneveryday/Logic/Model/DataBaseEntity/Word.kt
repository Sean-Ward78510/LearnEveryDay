package com.example.learneveryday.Logic.Model.DataBaseEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 11:20
 * description
 */
@Entity
data class Word(var content: String? = null, var translate : String? = null) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}