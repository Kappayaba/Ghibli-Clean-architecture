package com.starscience.cuisinietmoi.cuisineetmoi.remote.mapper

import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.remote.model.GhibliRemote

class GhibliRemoteMapper : RemoteMapper<GhibliRemote, Ghibli> {
    override fun mapFromRemote(type: GhibliRemote): Ghibli {
        return Ghibli(type.id, type.title, type.description, type.director, type.producer, type.release_date, type.rt_score)
    }
}