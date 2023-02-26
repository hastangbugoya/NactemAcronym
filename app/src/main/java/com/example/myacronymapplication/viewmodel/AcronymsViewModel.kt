package com.example.myacronymapplication.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myacronymapplication.data.Lf
import com.example.myacronymapplication.data.NactemResponseItem
import com.example.myacronymapplication.network.NactemRetofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AcronymsViewModel() : ViewModel() {
    var longFormList: MutableLiveData<List<Lf>> =
        MutableLiveData<List<Lf>>().apply { value = emptyList() }
    var exception = MutableLiveData<Exception?>().apply { value = null }
    var error = MutableLiveData<String?>().apply { value = null }

    fun getFullForm(sf: String) {
        viewModelScope.launch {
            try {
                // ensure IO is done on the IO thread
                val response = withContext(Dispatchers.IO) {
                    NactemRetofit.getService().getFullForm()
                }
                if (response.isSuccessful) {
                    // successful!
                    longFormList.value = response.body()?.get(0)?.lfs ?: listOf()
                    exception.value = null
                    error.value = null
                } else {
                    longFormList.value = listOf()
                    error.value = response.code().toString()
                }
            } catch (e: Exception) {
                longFormList.value = listOf()
                exception.value = e
                error.value = e.toString()
            }
        }
    }

    interface ToastCallback {
        fun showToast(message: String)
    }
}

