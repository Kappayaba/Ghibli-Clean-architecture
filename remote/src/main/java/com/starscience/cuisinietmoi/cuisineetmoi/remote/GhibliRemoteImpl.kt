package com.starscience.cuisinietmoi.cuisineetmoi.remote

import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.data.source.GhibliDataStoreInterface
import com.starscience.cuisinietmoi.cuisineetmoi.remote.mapper.GhibliRemoteMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import sun.rmi.runtime.Log
import java.lang.UnsupportedOperationException

class GhibliRemoteImpl(private val ghibliService: GhibliService, private val ghibliMapper: GhibliRemoteMapper) : GhibliDataStoreInterface {

    override fun getGhibli(): Flowable<List<Ghibli>> {
        return ghibliService.getGhibli()
            .map { it.values }
            .map {
                val entities = mutableListOf<Ghibli>()
                it.forEach{ entities.add(ghibliMapper.mapFromRemote(it)) }
                entities
            }
    }

    override fun saveGhibli(ghiblis: List<Ghibli>): Completable {
        throw UnsupportedOperationException()
    }

    override fun clearGhibli(): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun setLastCacheTime(lastCache: Long) {
        throw UnsupportedOperationException()
    }

    override fun isExpired(): Boolean {
        throw UnsupportedOperationException()
    }
}