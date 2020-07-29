package com.starscience.cuisinietmoi.cuisineetmoi.remote.mapper

interface RemoteMapper<in R, out M> {

    fun mapFromRemote(type: R) : M

}