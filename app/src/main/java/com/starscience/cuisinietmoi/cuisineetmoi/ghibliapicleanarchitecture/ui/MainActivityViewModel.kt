package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.starscience.cuisinietmoi.cuisineetmoi.data.usecases.interactor.GetGhibli
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.model.GhibliState
import io.reactivex.disposables.Disposable
import timber.log.Timber

class MainActivityViewModel(private val getGhibli: GetGhibli) : ViewModel(){

    private val ghibliLiveData: MutableLiveData<GhibliState> = MutableLiveData()
    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getGhiblis() : LiveData<GhibliState>{
        return ghibliLiveData
    }

    fun fetchGhiblis() {
        ghibliLiveData.postValue(GhibliState.Loading)
        disposable = getGhibli.execute()
            .subscribe(
                { data -> ghibliLiveData.postValue(GhibliState.Success(data)) },
                { error -> ghibliLiveData.postValue(GhibliState.Error(error.message)) }
            )
    }

}