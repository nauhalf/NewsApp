package com.nauhalf.newsapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@SuppressLint("SimpleDateFormat")
fun String.toDate(
    baseFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    baseTimeZone: TimeZone = TimeZone.getTimeZone("UTC"),
): Date? {
    return try {
        if (this.isEmpty()) return null
        val simpleDateFormat = SimpleDateFormat(baseFormat)
        simpleDateFormat.timeZone = baseTimeZone
        simpleDateFormat.parse(this)
    } catch (_: Exception) {
        null
    }
}

@SuppressLint("SimpleDateFormat")
fun Date?.toFormat(
    format: String = "EEE, dd MMM yyyy",
    timeZone: TimeZone = TimeZone.getDefault(),
    locale: Locale = Locale.getDefault(),
    nullValue: String = "-",
): String {
    return try {
        if (this == null)
            return nullValue
        val simpleDateFormat = SimpleDateFormat(format, locale)
        simpleDateFormat.timeZone = timeZone
        simpleDateFormat.format(this)
    } catch (_: Exception) {
        nullValue
    }
}
