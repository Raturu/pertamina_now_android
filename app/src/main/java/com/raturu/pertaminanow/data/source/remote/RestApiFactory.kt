package com.raturu.pertaminanow.data.source.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created on : April 09, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
object RestApiFactory {

    fun makeRestApi(baseUrl: String, okHttpClient: OkHttpClient): RestApi {
        return makeService(baseUrl, okHttpClient, makeGson())
    }

    private fun makeService(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): RestApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(RestApi::class.java)
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }
}