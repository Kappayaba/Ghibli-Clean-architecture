package com.starscience.cuisinietmoi.cuisineetmoi.cache.dao

import androidx.room.*
import com.starscience.cuisinietmoi.cuisineetmoi.cache.model.GhibliCache
import com.starscience.cuisinietmoi.cuisineetmoi.cache.utils.QUERY_DELETE_GHIBLIS
import com.starscience.cuisinietmoi.cuisineetmoi.cache.utils.QUERY_GET_GHIBLIS
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CacheGhibliDao {

    @Query(QUERY_GET_GHIBLIS)
    fun getGhibli() : List<GhibliCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGhibli(cachedGhibli: GhibliCache)

    @Query(QUERY_DELETE_GHIBLIS)
    fun deleteGhibli()
}