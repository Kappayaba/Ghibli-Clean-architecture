package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.executor


import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.ThreadExecutor
import java.util.concurrent.*


open class JobExecutor() : ThreadExecutor {

    private val workQueue = LinkedBlockingDeque<Runnable>()

    private val threadFactory = ThreadFactory { command -> Thread(command, "android") }

    private val threadPoolExecutor = ThreadPoolExecutor(
        CORE_POOL_SIZE, CORE_POOL_SIZE + 2, 10,
        TimeUnit.SECONDS, workQueue, threadFactory)

    override fun execute(command: Runnable) {
        threadPoolExecutor.execute(command)
    }

    companion object{
        private val CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors()
    }
}