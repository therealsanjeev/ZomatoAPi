package com.therealsanjeev.zomato.repo

import com.therealsanjeev.zomato.Api.RetrofitInstance
import com.therealsanjeev.zomato.model.SearchModel
import retrofit2.Response

class Repository {

    suspend fun getSearchResponse(query: String): Response<SearchModel> {
        return RetrofitInstance.api.getSearchResponse(query)
    }
}