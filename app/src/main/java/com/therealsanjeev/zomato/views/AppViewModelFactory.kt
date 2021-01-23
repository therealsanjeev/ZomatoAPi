package com.therealsanjeev.zomato.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.therealsanjeev.zomato.repo.Repository

class AppViewModelFactory(private val repository:Repository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(repository) as T
    }

}