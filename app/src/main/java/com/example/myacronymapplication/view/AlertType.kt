package com.example.myacronymapplication.view

import com.example.myacronymapplication.R
/**
 * Alert types and snackbar colors
 * @param bgColor resource id for background color
 * @param fgColor resource id for text color
 */
enum class AlertType(val bgColor: Int, val fgColor: Int) {
    DEFAULT(R.color.green_opaque, R.color.white),
    ERROR(R.color.red_opaque, R.color.white)
}