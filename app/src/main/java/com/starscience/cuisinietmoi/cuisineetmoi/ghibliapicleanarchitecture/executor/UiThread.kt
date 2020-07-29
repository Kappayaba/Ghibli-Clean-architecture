package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.executor

import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UiThread : PostExecutionThread {
    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}