package com.starscience.cuisinietmoi.cuisineetmoi.data.usecases

import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.PostExecutionThread
import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCases<in Params> protected constructor(private val threadExecutor: ThreadExecutor, private val postExecutionThread: PostExecutionThread){

    private val subscription = Disposables.empty()

    abstract fun buildUseCaseCompletable(params: Params? = null) : Completable

    fun execute(observer: DisposableCompletableObserver, params: Params? = null) : Completable {
        return buildUseCaseCompletable(params)
            .observeOn(postExecutionThread.scheduler)
            .subscribeOn(Schedulers.from(threadExecutor))
    }

    fun unsubscribe(){
        if(!subscription.isDisposed){
            subscription.dispose()
        }
    }
}