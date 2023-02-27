package com.example.myacronymapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher

class AcronymsViewModelFactory(private val dispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AcronymsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AcronymsViewModel(dispatcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}