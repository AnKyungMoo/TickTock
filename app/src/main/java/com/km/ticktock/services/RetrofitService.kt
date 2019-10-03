package com.km.ticktock.services

import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun restAPI(): SearchInterface {
        return retrofitInterface().create(SearchInterface::class.java)
    }

    private val httpClient = OkHttpClient.Builder()
        .addNetworkInterceptor { chain: Interceptor.Chain -> chain.proceed(chain.request().newBuilder().build()) }

    /* TODO: Singleton으로 변경하자 */
    private fun retrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(httpClient.build())
            .build()
    }
}