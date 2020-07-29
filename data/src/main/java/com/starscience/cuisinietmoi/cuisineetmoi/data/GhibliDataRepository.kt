package com.starscience.cuisinietmoi.cuisineetmoi.data

import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.data.repository.GhibliRepository
import com.starscience.cuisinietmoi.cuisineetmoi.data.source.GhibliDataStoreFactory
import io.reactivex.Completable
import io.reactivex.Flowable

class GhibliDataRepository(private val factory: GhibliDataStoreFactory) : GhibliRepository {

    override fun getGhibli(): Flowable<List<Ghibli>> {
        return factory.retrieveCacheDataStore().isCached()
            .flatMapPublisher {
                factory.retrieveDataStore(it).getGhibli()
            }
            .flatMap { saveGhibli(it).toSingle{ it }.toFlowable() }
    }

    override fun saveGhibli(ghiblis: List<Ghibli>): Completable {
        return factory.retrieveCacheDataStore().saveGhibli(ghiblis)
    }

    override fun deleteGhibli(): Completable {
        return factory.retrieveCacheDataStore().clearGhibli()
    }
}