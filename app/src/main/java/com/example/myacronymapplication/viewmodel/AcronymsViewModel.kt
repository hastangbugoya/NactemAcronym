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


class AcronymsViewModel : ViewModel() {
    lateinit var toastCallback : ToastCallback
    var longFormList: MutableLiveData<List<Lf>> =
        MutableLiveData<List<Lf>>().apply { value = listOf() }

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
                    toastCallback.showToast("${longFormList.value!!.size} items found")
                    jLog("List : $response")
                } else {
                    longFormList.value = listOf()
                    toastCallback.showToast("Zero items found")
                }
            } catch (e: Exception) {
                longFormList.value = listOf()
                toastCallback.showToast("Exception encountered : $e")
            }
        }
    }

    @JvmName("setToastCallback1")
    fun setToastCallback(tcb : ToastCallback) {
        toastCallback = tcb
    }

    interface ToastCallback {
        fun showToast(message: String)
    }
}

