package com.starscience.cuisinietmoi.cuisineetmoi.data.usecases

import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.PostExecutionThread
import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCases<T, in Params> constructor(private val threadExecutor: ThreadExecutor,
                       private val postExecutionThread: PostExecutionThread){

    abstract fun buildCompletableUseCases(params: Params? = null): Flowable<T>

    open fun execute(params: Params? = null) : Flowable<T>{
        return buildCompletableUseCases(params)
            .observeOn(postExecutionThread.scheduler)
            .subscribeOn(Schedulers.from(threadExecutor))
    }
}