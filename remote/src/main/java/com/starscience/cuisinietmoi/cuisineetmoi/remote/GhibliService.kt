package com.starscience.cuisinietmoi.cuisineetmoi.remote

import com.google.gson.internal.LinkedTreeMap
import com.starscience.cuisinietmoi.cuisineetmoi.remote.model.GhibliRemote
import io.reactivex.Flowable
import retrofit2.http.GET
import sun.rmi.runtime.Log

interface GhibliService {

    @GET("film.json")
    fun getGhibli() : Flowable<LinkedTreeMap<String, GhibliRemote>>

}