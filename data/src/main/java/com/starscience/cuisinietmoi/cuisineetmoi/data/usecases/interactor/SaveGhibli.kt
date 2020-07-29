package com.starscience.cuisinietmoi.cuisineetmoi.data.usecases.interactor

import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.PostExecutionThread
import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.ThreadExecutor
import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.data.repository.GhibliRepository
import com.starscience.cuisinietmoi.cuisineetmoi.data.usecases.CompletableUseCases
import io.reactivex.Completable
import java.lang.Exception

open class SaveGhibli(private val ghibliRepository: GhibliRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) :
    CompletableUseCases<List<Ghibli>>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseCompletable(params: List<Ghibli>?): Completable {
        if (params != null){
            return ghibliRepository.saveGhibli(params)
        }
        // return Completable.complete() (Can be a solution)
        throw Exception("## DATA ERROR ##\n# SAVE GHIBLI USE CASES #\nNULL PARAMETERS NOT ACCEPTED")
    }

}