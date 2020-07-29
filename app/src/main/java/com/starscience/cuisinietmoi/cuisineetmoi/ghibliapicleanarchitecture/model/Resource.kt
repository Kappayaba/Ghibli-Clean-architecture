package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.model

open class Resource<out T> constructor(val status: ResourceState, val data: T?, val message: String?) {

    fun <T> success(data: T) : Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> loading() : Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }

    fun <T> error(message: String, data: T) : Resource<T> {
        return Resource(ResourceState.SUCCESS, null, message)
    }
}