package com.example.myacronymapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myacronymapplication.databinding.ActivityMainBinding
import com.example.myacronymapplication.view.LongFormMainAdapter
import com.example.myacronymapplication.viewmodel.AcronymsViewModel

class MainActivity : AppCompatActivity(), AcronymsViewModel.ToastCallback {
    val myViewModel: AcronymsViewModel by viewModels<AcronymsViewModel>()
    val binding : ActivityMainBinding by lazy  {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    val inputMethodManager : InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)

        val myLFAdapter = LongFormMainAdapter()
        binding.resultRecycler.adapter = myLFAdapter

        myViewModel.setToastCallback(this)

        myViewModel.longFormList.observeForever() {
            myLFAdapter.setLFList(it)
        }

        binding.submitButton.setOnClickListener {
            searchResults(binding.inputText.text.toString(), view.windowToken,0)
        }

        binding.inputText.setOnEditorActionListener  { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchResults(binding.inputText.text.toString(), view.windowToken,0)
                true
            } else {
                false
            }
        }

    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun searchResults(s : String, v : IBinder, f : Int) {
        binding.inputText.setText(binding.inputText.text.toString().trim())
        inputMethodManager.hideSoftInputFromWindow(v, f)
        myViewModel.getFullForm(s)
    }

}