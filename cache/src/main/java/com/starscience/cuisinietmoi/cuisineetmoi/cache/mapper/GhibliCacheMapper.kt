package com.starscience.cuisinietmoi.cuisineetmoi.cache.mapper

import com.starscience.cuisinietmoi.cuisineetmoi.cache.model.GhibliCache
import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli

class GhibliCacheMapper : CacheMapper<GhibliCache, Ghibli> {
    override fun mapToCache(type: Ghibli): GhibliCache {
        return GhibliCache(type.id, type.title, type.description, type.director, type.producer, type.releaseDate, type.rt_score)
    }

    override fun mapFromCache(type: GhibliCache): Ghibli {
        return Ghibli(type.id, type.title, type.description, type.director, type.producer, type.release_date, type.rt_score)
    }
}