package com.example.myacronymapplication.viewmodel

import android.view.inputmethod.EditorInfo
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myacronymapplication.data.Lf
import com.example.myacronymapplication.network.NactemRetofit
import com.example.myacronymapplication.utility.Logger.jLog
import com.example.myacronymapplication.data.AlertType
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


class AcronymsViewModel : ViewModel(), Observable {

    private var interfaceImplementation: UIUpdates? = null
    @Bindable
    var longFormList: MutableLiveData<List<Lf>> =
        MutableLiveData<List<Lf>>().apply { value = listOf() }
    @Bindable
    var userInput = MutableLiveData<String>().apply { value = "" }

    fun getFullForm(searchString: String) {
        if ((userInput.value?.trim()?.length ?: 0) > 0)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = async {
                        NactemRetofit.getService().getFullForm(searchString)
                    }.await()
                    if (response.isNotEmpty())
                        longFormList.postValue(response[0].lfs)
                    else
                        longFormList.postValue(listOf())
                    withContext(Dispatchers.Main) {
                        interfaceImplementation?.showAlert("${longFormList.value?.size ?: 0} items found", AlertType.DEFAULT)
                    }
                } catch (e: Exception) {
                    longFormList.postValue(listOf())
                    val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                        .format(Date(System.currentTimeMillis()))
                    jLog("$searchString : $formattedDate : ${e.toString()}")
                    interfaceImplementation?.showAlert("Exception encountered : $e", AlertType.ERROR)
                }
        } else {
            interfaceImplementation?.showAlert("Acronym cannot be blank", AlertType.ERROR)
        }
    }

    fun startSearch() {
        getFullForm(userInput.value.toString())
        interfaceImplementation?.hideTheKeyBoard()
    }

    fun onAcronymEditTextAction(actionId: Int) : Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            startSearch()
            return true
        }
        return false
    }

    fun setUpdateImplementer(implementer: UIUpdates) {
        interfaceImplementation = implementer
    }

    interface UIUpdates {
        fun showAlert(message: String, type : AlertType)
        fun hideTheKeyBoard()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}

