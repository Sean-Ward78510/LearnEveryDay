package com.example.learneveryday.Logic.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learneveryday.LearnEveryday
import com.example.learneveryday.Logic.Dao.RecordItemDao
import com.example.learneveryday.Logic.Dao.WordDao
import com.example.learneveryday.Logic.Model.DataBaseEntity.RecordItem
import com.example.learneveryday.Logic.Model.DataBaseEntity.Word

/**
 * @author 古娜拉·涤丑
 * @date 2024-07-31 12:00
 * description
 */
@Database(version = 1, entities = [Word::class,RecordItem::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun wordDao() : WordDao
    abstract fun recordItemDao(): RecordItemDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase():AppDatabase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(LearnEveryday.context,AppDatabase::class.java,"app_database")//context.applicationContext
                .build().apply {
                    instance = this
                }
        }
    }
}