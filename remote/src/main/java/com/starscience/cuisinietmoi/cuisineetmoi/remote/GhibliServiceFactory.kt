package com.starscience.cuisinietmoi.cuisineetmoi.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GhibliServiceFactory {

    fun makeGhibliService(isDebug: Boolean) : GhibliService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor(isDebug))
        return makeGhibliServiceAPI(okHttpClient, makeGson())
    }

    private fun makeGhibliServiceAPI(okHttpClient: OkHttpClient, gson: Gson) : GhibliService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://messenger-de-la-street.firebaseio.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(GhibliService::class.java)
    }

    private fun makeOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean) : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (isDebug)
            HttpLoggingInterceptor.Level.BASIC
        else
            HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    private fun makeGson() : Gson {
        return GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
}