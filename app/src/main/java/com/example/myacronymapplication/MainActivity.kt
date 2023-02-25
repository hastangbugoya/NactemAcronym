package com.example.myacronymapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myacronymapplication.viewmodel.AcronymsViewModel

class MainActivity : AppCompatActivity(), AcronymsViewModel.ToastCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}