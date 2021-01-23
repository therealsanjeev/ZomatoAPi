package com.therealsanjeev.zomato.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.therealsanjeev.zomato.model.SearchModel
import com.therealsanjeev.zomato.repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AppViewModel(private val repository: Repository): ViewModel() {

    val apiResponse: MutableLiveData<Response<List<SearchModel>>> = MutableLiveData()
    val anotherApiResponse: MutableLiveData<SearchModel> = MutableLiveData()

    val newApiResponse: MutableLiveData<Response<SearchModel>> = MutableLiveData()
    fun getSearchResponse(query: String){
        viewModelScope.launch {
            val newResponse = repository.getSearchResponse(query)
            newApiResponse.value = newResponse
        }
    }
}