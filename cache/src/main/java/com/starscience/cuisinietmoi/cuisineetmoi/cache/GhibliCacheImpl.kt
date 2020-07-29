package com.starscience.cuisinietmoi.cuisineetmoi.cache

import com.starscience.cuisinietmoi.cuisineetmoi.cache.db.GhibliDatabase
import com.starscience.cuisinietmoi.cuisineetmoi.cache.mapper.GhibliCacheMapper
import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.data.source.GhibliDataStoreInterface
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class GhibliCacheImpl constructor(val db: GhibliDatabase,
                                  private val mapper: GhibliCacheMapper,
                                  private val prefHelper: PreferencesHelper) : GhibliDataStoreInterface {
    private val EXPIRATION_TIME = (3600 * 16).toLong()

    override fun getGhibli(): Flowable<List<Ghibli>> {
        return Flowable.defer{
            Flowable.just(db.cacheGhibliDao().getGhibli())
        }.map {
            it.map{
                mapper.mapFromCache(it)
            }
        }
    }

    override fun saveGhibli(ghiblis: List<Ghibli>): Completable {
        return Completable.defer{
            ghiblis.forEach{
                db.cacheGhibliDao().saveGhibli(mapper.mapToCache(it))
            }
            Completable.complete()
        }
    }

    override fun clearGhibli(): Completable {
        return Completable.defer{
            db.cacheGhibliDao().deleteGhibli()
            Completable.complete()
        }
    }

    override fun isCached(): Single<Boolean> {
        return Single.defer{
            Single.just(db.cacheGhibliDao().getGhibli().isNotEmpty())
        }
    }

    override fun setLastCacheTime(lastCache: Long) {
        prefHelper.lastCachedTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = prefHelper.lastCachedTime
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }
}