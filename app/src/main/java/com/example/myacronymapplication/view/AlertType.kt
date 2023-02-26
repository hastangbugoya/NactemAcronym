package com.example.myacronymapplication.view

import com.example.myacronymapplication.R

enum class AlertType(val brgb: Int, val frgb: Int) {
    DEFAULT(R.color.green_opaque, R.color.white),
    ERROR(R.color.red_opaque, R.color.white)
}