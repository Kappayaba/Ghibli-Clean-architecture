package com.starscience.cuisinietmoi.cuisineetmoi.data.source

import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

open class GhibliDataStoreFactory(private val cacheDataStore: GhibliDataStoreInterface, private val remoteDataStore: GhibliDataStoreInterface) {

    // Todo: Switch function by instance of cache and remote
    open fun retrieveDataStore(isCached: Boolean) : GhibliDataStoreInterface {
        if(isCached && !cacheDataStore.isExpired()){
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    open fun retrieveCacheDataStore() : GhibliDataStoreInterface {
        return cacheDataStore
    }

    open fun retrieveRemoteDataStore() : GhibliDataStoreInterface {
        return remoteDataStore
    }

}