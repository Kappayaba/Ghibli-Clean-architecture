package com.starscience.cuisinietmoi.cuisineetmoi.cache.mapper

interface CacheMapper<C, E> {

    fun mapToCache(type: E) : C

    fun mapFromCache(type: C) : E

}