package com.starscience.cuisinietmoi.cuisineetmoi.data.usecases.interactor

import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.PostExecutionThread
import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.ThreadExecutor
import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.data.repository.GhibliRepository
import com.starscience.cuisinietmoi.cuisineetmoi.data.usecases.FlowableUseCases
import io.reactivex.Flowable

open class GetGhibli(private val ghibliRepository: GhibliRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) :
    FlowableUseCases<List<Ghibli>, Void?>(threadExecutor, postExecutionThread){

    override fun buildCompletableUseCases(params: Void?): Flowable<List<Ghibli>> {
        return ghibliRepository.getGhibli()
    }

}