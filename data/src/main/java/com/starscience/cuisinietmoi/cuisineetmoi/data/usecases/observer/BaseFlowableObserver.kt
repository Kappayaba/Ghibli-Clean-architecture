package com.starscience.cuisinietmoi.cuisineetmoi.data.usecases.observer

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

open class BaseFlowableObserver<T> : SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {}

    override fun onSuccess(t: T) {}

    override fun onError(e: Throwable) {}
}