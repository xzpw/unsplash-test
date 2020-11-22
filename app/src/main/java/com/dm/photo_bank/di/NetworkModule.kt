package com.dm.photo_bank.di

import com.dm.photo_bank.api.Config.BASE_URL
import com.dm.photo_bank.api.UnsplashApi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netWorkModule = module {

    single<Call.Factory> { okHttp() }
    single { retrofit(get()) }
    single { apiProvider(get()) }
}

fun okHttp() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()

fun retrofit(callFactory: Call.Factory) = Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun apiProvider(retrofit: Retrofit) = retrofit.create(UnsplashApi::class.java)