package com.example.myacronymapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myacronymapplication.databinding.ActivityMainBinding
import com.example.myacronymapplication.view.LongFormMainAdapter
import com.example.myacronymapplication.viewmodel.AcronymsViewModel

class MainActivity : AppCompatActivity(), AcronymsViewModel.ToastCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)

        val myLFAdapter = LongFormMainAdapter()
        binding.resultRecycler.adapter = myLFAdapter
        val myViewModel : AcronymsViewModel by viewModels<AcronymsViewModel>()

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        myViewModel.longFormList.observeForever() {
            myLFAdapter.setLFList(it)
        }

        binding.submitButton.setOnClickListener {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        myViewModel.getFullForm("ASAP")
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}