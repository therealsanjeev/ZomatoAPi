package com.therealsanjeev.zomato.Api

import com.therealsanjeev.zomato.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ZomatoApiService {

    @GET("search?")
    suspend fun getSearchResponse(@Query("q") key :String):Response<SearchModel>
}