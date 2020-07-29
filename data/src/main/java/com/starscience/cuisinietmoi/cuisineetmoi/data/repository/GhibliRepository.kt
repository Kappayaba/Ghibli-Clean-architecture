package com.starscience.cuisinietmoi.cuisineetmoi.data.repository

import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import io.reactivex.Completable
import io.reactivex.Flowable

interface GhibliRepository {

    fun getGhibli() : Flowable<List<Ghibli>>

    fun saveGhibli(ghiblis: List<Ghibli>) : Completable

    fun deleteGhibli() : Completable
}