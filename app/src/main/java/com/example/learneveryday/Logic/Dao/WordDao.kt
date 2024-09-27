package com.example.learneveryday.Logic.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.learneveryday.Logic.Model.DataBaseEntity.Word

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 11:33
 * description
 */
@Dao
interface WordDao {

    @Insert
    fun insertWord(word: Word) : Long

    @Query("select * from Word order by id desc")
    fun loadCollectWord(): List<Word>

    @Query("select * from Word where content like :string || '%' order by id desc")
    fun queryWord(string: String) : List<Word>

    @Query("delete from Word where content = :content ")
    fun deleteWord(content: String)
}