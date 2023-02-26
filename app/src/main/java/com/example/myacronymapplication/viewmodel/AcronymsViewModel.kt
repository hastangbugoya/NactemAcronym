package com.example.myacronymapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myacronymapplication.data.Lf
import com.example.myacronymapplication.network.NactemRetofit
import com.example.myacronymapplication.utility.Logger.jLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AcronymsViewModel() : ViewModel() {
    var longFormList: MutableLiveData<List<Lf>> =
        MutableLiveData<List<Lf>>().apply { value = listOf() }
    var exception = MutableLiveData<Exception?>().apply { value = null }
    var error = MutableLiveData<String?>().apply { value = null }

    fun getFullForm(sf: String) {
        jLog("getFullForm--------------------")
        viewModelScope.launch {
            try {
                // ensure IO is done on the IO thread
                val response = withContext(Dispatchers.IO) {
                    NactemRetofit.getService().getFullForm(sf)
                }
                jLog("Reply List : $response")
                if (!response.isEmpty()) {
                    // successful!
                    longFormList.value = response.get(0)?.lfs ?: listOf()
                    exception.value = null
                    error.value = null
                    jLog("List : $response")
                } else {
                    longFormList.value = listOf()
                    error.value = "Zero results found"
                }
            } catch (e: Exception) {
                longFormList.value = listOf()
                exception.value = e
                error.value = e.toString()
                jLog("Exception >>>>> " + e.toString())
            }
        }
    }

    interface ToastCallback {
        fun showToast(message: String)
    }
}

