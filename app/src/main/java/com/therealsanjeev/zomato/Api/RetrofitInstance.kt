package com.therealsanjeev.zomato.Api

import com.therealsanjeev.zomato.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(ApiInterceptor())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val api: ZomatoApiService by lazy {
        retrofit.create(ZomatoApiService::class.java)
    }
}