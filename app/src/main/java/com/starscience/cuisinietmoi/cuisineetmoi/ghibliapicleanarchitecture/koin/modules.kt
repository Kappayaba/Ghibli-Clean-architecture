package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.koin

import android.os.Debug
import androidx.room.Room
import androidx.room.RoomDatabase
import com.starscience.cuisinietmoi.cuisineetmoi.cache.BuildConfig
import com.starscience.cuisinietmoi.cuisineetmoi.cache.GhibliCacheImpl
import com.starscience.cuisinietmoi.cuisineetmoi.cache.PreferencesHelper
import com.starscience.cuisinietmoi.cuisineetmoi.cache.db.GhibliDatabase
import com.starscience.cuisinietmoi.cuisineetmoi.cache.mapper.GhibliCacheMapper
import com.starscience.cuisinietmoi.cuisineetmoi.data.GhibliDataRepository
import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.PostExecutionThread
import com.starscience.cuisinietmoi.cuisineetmoi.data.executor.ThreadExecutor
import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.data.repository.GhibliRepository
import com.starscience.cuisinietmoi.cuisineetmoi.data.source.GhibliDataStoreFactory
import com.starscience.cuisinietmoi.cuisineetmoi.data.source.GhibliDataStoreInterface
import com.starscience.cuisinietmoi.cuisineetmoi.data.usecases.interactor.GetGhibli
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.executor.JobExecutor
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.executor.UiThread
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.ui.Adapter
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.ui.MainActivityViewModel
import com.starscience.cuisinietmoi.cuisineetmoi.remote.GhibliRemoteImpl
import com.starscience.cuisinietmoi.cuisineetmoi.remote.GhibliServiceFactory
import com.starscience.cuisinietmoi.cuisineetmoi.remote.mapper.GhibliRemoteMapper
import io.reactivex.Single
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val applicationModule = module{
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UiThread() }

    single { Room.databaseBuilder(androidContext().applicationContext, GhibliDatabase::class.java, "ghibli.db").build() }

    single { PreferencesHelper(androidContext().applicationContext) }
    factory { GhibliCacheMapper() }

    factory { GhibliServiceFactory.makeGhibliService(BuildConfig.DEBUG) }
    factory { GhibliRemoteMapper() }

    factory<GhibliDataStoreInterface>(named("cache")) { GhibliCacheImpl(get(), get(), get()) }
    factory<GhibliDataStoreInterface>(named("remote")) { GhibliRemoteImpl(get(), get()) }
    factory { GhibliDataStoreFactory(get(named("cache")), get(named("remote"))) }

    factory<GhibliRepository>{ GhibliDataRepository(get()) }
}

val ghibliModule = module {
    factory { Adapter() }
    factory { GetGhibli(get(), get(), get()) }
    viewModel { MainActivityViewModel(get()) }
}