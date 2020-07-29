package com.starscience.cuisinietmoi.cuisineetmoi.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.starscience.cuisinietmoi.cuisineetmoi.cache.dao.CacheGhibliDao
import com.starscience.cuisinietmoi.cuisineetmoi.cache.model.GhibliCache


// Todo: In case of issue with room change the singleton

@Database(entities = [GhibliCache::class], version = 1)
abstract class GhibliDatabase : RoomDatabase(){

    abstract fun cacheGhibliDao() : CacheGhibliDao

    private var INSTANCE: GhibliDatabase? = null

    private val slock = Any()

    fun getInstance(context: Context) : GhibliDatabase{
        if (INSTANCE != null){
            return INSTANCE!!
        }
        synchronized(slock){
            if(INSTANCE != null){
                return INSTANCE!!
            }
            INSTANCE = Room.databaseBuilder(context.applicationContext, GhibliDatabase::class.java, "ghibli.db")
                .build()

            return INSTANCE!!
        }
    }
}