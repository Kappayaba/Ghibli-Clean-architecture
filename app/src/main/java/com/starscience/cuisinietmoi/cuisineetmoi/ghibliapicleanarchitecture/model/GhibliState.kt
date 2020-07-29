package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.model

import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli

sealed class GhibliState(val resourceState: ResourceState, val data: List<Ghibli>? = null, val errorMessage: String? = null){

    data class Success(private val ghiblis: List<Ghibli>) : GhibliState(ResourceState.SUCCESS, ghiblis)

    object Loading : GhibliState(ResourceState.LOADING)

    data class Error(private val message: String?) : GhibliState(ResourceState.ERROR, errorMessage = message)
}