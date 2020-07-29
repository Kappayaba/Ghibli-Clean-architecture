package com.starscience.cuisinietmoi.cuisineetmoi.data.source

import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface GhibliDataStoreInterface {

    fun getGhibli() : Flowable<List<Ghibli>>

    fun saveGhibli(ghiblis: List<Ghibli>) : Completable

    fun clearGhibli() : Completable

    //TODO : Why single ?
    fun isCached() : Single<Boolean>

    fun setLastCacheTime(lastCache: Long)

    fun isExpired() : Boolean
}