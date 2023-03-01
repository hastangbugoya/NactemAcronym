package com.example.myacronymapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.myacronymapplication.databinding.ActivityMainBinding
import com.example.myacronymapplication.data.AlertType
import com.example.myacronymapplication.view.LongFormMainAdapter
import com.example.myacronymapplication.viewmodel.AcronymsViewModel
import com.example.myacronymapplication.viewmodel.AcronymsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), AcronymsViewModel.UIUpdates {

    private val myViewModel: AcronymsViewModel by viewModels {
        AcronymsViewModelFactory(Dispatchers.IO)
    }
    private val databinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myLFAdapter = LongFormMainAdapter(this)
        databinding.resultRecycler.adapter = myLFAdapter

        myViewModel.setToastCallback(this)

        databinding.dbAcronymsModel = myViewModel

        myViewModel.longFormList.observeForever {
            myLFAdapter.setLFList(it)
        }

        myViewModel.userInput.observeForever {
            if (BuildConfig.DEBUG)
                databinding.submitButton.setText(
                    getString(
                        R.string.search_string_format,
                        myViewModel.userInput.value.toString()
                    )
                )
        }

        //this is to just for onscreen keyboard
        databinding.inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchResults(myViewModel.userInput.value.toString())
                true
            } else {
                false
            }
        }


    }

    override fun showAlert(message: String, type: AlertType) {
        Snackbar.make(databinding.inputText, message, 5000)
            .setBackgroundTint(getColor(type.bgColor))
            .setTextColor(getColor(type.fgColor))
            .show()
    }

    override fun hideTheKeyBoard() {
        inputMethodManager.hideSoftInputFromWindow(databinding.root.windowToken, 0)
    }

    private fun searchResults(s: String) {
        myViewModel.getFullForm(s)
    }

}