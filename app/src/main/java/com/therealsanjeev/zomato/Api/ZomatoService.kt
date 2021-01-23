package com.therealsanjeev.zomato.Api

import retrofit2.http.GET
import retrofit2.http.Query

interface ZomatoService {
    @GET("Search")
    suspend fun getSearch(@Query("key") key :String,@Query(""))
}