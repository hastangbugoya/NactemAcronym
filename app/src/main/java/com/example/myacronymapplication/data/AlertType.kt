package com.example.myacronymapplication.data

import com.example.myacronymapplication.R
/**
 * Alert types and snackbar colors
 * @param bgColor resource id for background color
 * @param fgColor resource id for text color
 */
enum class AlertType(val bgColor: Int, val fgColor: Int) {
    DEFAULT(R.color.default_alert_opaque, R.color.white),
    ERROR(R.color.error_alert_opaque, R.color.white)
}