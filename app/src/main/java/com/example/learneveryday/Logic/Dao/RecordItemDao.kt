package com.example.learneveryday.Logic.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.learneveryday.Logic.Model.DataBaseEntity.RecordItem

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 11:54
 * description
 */
@Dao
interface RecordItemDao {

    @Insert
    fun insertRecordItem(recordItem: RecordItem) : Long

    @Query("select * from RecordItem order by id desc")
    fun loadAllRecordItem(): List<RecordItem>

    @Query("delete from RecordItem where id = :id")
    fun deleteRecordItemById(id: Long)

    @Query("select * from RecordItem where title like '%' || :string || '%' order by id desc")
    fun queryRecordItem(string: String): List<RecordItem>
}