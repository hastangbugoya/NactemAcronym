package com.example.myacronymapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myacronymapplication.data.Lf
import com.example.myacronymapplication.network.NactemRetofit
import com.example.myacronymapplication.utility.Logger.jLog
import com.example.myacronymapplication.data.AlertType
import kotlinx.coroutines.*


class AcronymsViewModel(private var dispatcher: CoroutineDispatcher) : ViewModel() {

    private var toastCallback: ToastCallback? = null
    var longFormList: MutableLiveData<List<Lf>> =
        MutableLiveData<List<Lf>>().apply { value = listOf() }

    fun getFullForm(sf: String) {
        viewModelScope.launch {
            try {
                // ensure IO is done on the IO thread
                val response = withContext(dispatcher) {
                    NactemRetofit.getService().getFullForm(sf)
                }
                if (response.isNotEmpty())
                    longFormList.value = response[0].lfs
                else
                    longFormList.value = listOf()
                toastCallback?.showAlert("${longFormList.value?.size ?: 0} items found", AlertType.DEFAULT)
            } catch (e: Exception) {
                longFormList.value = listOf()
                jLog(e.toString())
                toastCallback?.showAlert("Exception encountered : $e", AlertType.ERROR)
            }
        }
    }


    @JvmName("setToastCallback1")
    fun setToastCallback(tcb: ToastCallback) {
        toastCallback = tcb
    }

    interface ToastCallback {
        fun showAlert(message: String, type : AlertType)
    }
}

