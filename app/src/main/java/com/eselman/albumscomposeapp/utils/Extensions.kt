package com.eselman.albumscomposeapp.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

fun String.extractYearFromDate(): Int =
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = LocalDate.parse(this)
            date.year
        } else {
            val simpleDateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.US)
            val date = simpleDateFormat.parse(this)
            date?.let {
                val calendar = Calendar.getInstance()
                calendar.time = date
                calendar.get(Calendar.YEAR)
            } ?: 0
        }
    } catch (e: Exception) {
        0
    }
