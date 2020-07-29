package com.starscience.cuisinietmoi.cuisineetmoi.data.executor

import io.reactivex.Scheduler

interface PostExecutionThread{
    val scheduler: Scheduler
}

