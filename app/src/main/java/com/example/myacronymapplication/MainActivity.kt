package com.example.myacronymapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.myacronymapplication.databinding.ActivityMainBinding
import com.example.myacronymapplication.data.AlertType
import com.example.myacronymapplication.view.LongFormMainAdapter
import com.example.myacronymapplication.viewmodel.AcronymsViewModel
import com.example.myacronymapplication.viewmodel.AcronymsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), AcronymsViewModel.ToastCallback {

    private val myViewModel: AcronymsViewModel by viewModels {
        AcronymsViewModelFactory(Dispatchers.IO)
    }
    private val databinding: ViewDataBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)

        val myLFAdapter = LongFormMainAdapter(this)
        binding.resultRecycler.adapter = myLFAdapter

        myViewModel.setToastCallback(this)
        binding.dbAcronymsModel = myViewModel

        myViewModel.longFormList.observeForever {
            myLFAdapter.setLFList(it)
        }

        myViewModel.userInput.observeForever {
            binding.submitButton.setText(getString(R.string.search_string_format, myViewModel.userInput.value.toString()))
        }

        binding.submitButton.setOnClickListener {
            searchResults(myViewModel.userInput.value.toString())
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        binding.inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchResults(myViewModel.userInput.value.toString())
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                true
            } else {
                false
            }
        }

    }

    override fun showAlert(message: String, type: AlertType) {
        Snackbar.make(binding.inputText, message, 5000)
            .setBackgroundTint(getColor(type.bgColor))
            .setTextColor(getColor(type.fgColor))
            .show()
    }

    private fun searchResults(s: String) {
        binding.inputText.setText(binding.inputText.text.toString().trim())
        myViewModel.getFullForm(s)
    }

}