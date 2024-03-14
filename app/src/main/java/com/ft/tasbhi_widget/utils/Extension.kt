package com.ft.tasbhi_widget.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.fragment.app.FragmentActivity
import com.ft.tasbhi_widget.R
import com.ft.tasbhi_widget.databinding.ViewSnackbarBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import java.util.Locale

enum class ColorType(@ColorRes val color: Int) {
    WHITE(color = R.color.white),
    BLACK(color = R.color.black),
    GREEN(color = R.color.lightGreen),
    RED(color = R.color.red)
}

fun Context.vibratePhone(duration: Long = 25) {
    try {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    duration,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(duration)
        }
    } catch (e: Exception) {
        Log.e("Vibration Exception", e.message.toString())
    }
}

fun Int?.orZero(): Int = this ?: 0

fun View.rotate(destinationRotate: Float, duration: Long = 150) {
    animate().rotation(destinationRotate).duration = duration
}

fun getTodayName(): String = when (Calendar.getInstance()[Calendar.DAY_OF_WEEK]) {
    1 -> "یکشنبه"
    2 -> "دوشنبه"
    3 -> "سه شنبه"
    4 -> "چهارشنبه"
    5 -> "پنجشنبه"
    6 -> "جمعه"
    7 -> "شنبه"
    else -> "شنبه"
}

fun getTodayZekr(): String = when (Calendar.getInstance()[Calendar.DAY_OF_WEEK]) {
    1 -> "یا ذوالجَلالِ وَ الاِکرام"
    2 -> "یا قاضِیَ الحاجات"
    3 -> "یا اَرحَمَ الرّاحِمین"
    4 -> "یا حَیُّ یا قَیّوم "
    5 -> "لا اِلهَ الَّا اَللهُ المَلِکُ الحَقُّ المُبین"
    6 -> "اَللهُمَ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّد"
    7 -> "یا رَبَّ العالَمین"
    else -> "اَللهُمَ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّد"
}

@SuppressLint("RestrictedApi")
fun Context.showSnackbar(view: View, message: String) {
    val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    val customSnackView = ViewSnackbarBinding.inflate(LayoutInflater.from(this)).apply {
        tvMessage.text = message
    }
    snackbar.view.setBackgroundColor(Color.TRANSPARENT)
    (snackbar.view as Snackbar.SnackbarLayout).apply {
        setPadding(0, 0, 0, 120)
        addView(customSnackView.root, 0)
    }
    snackbar.show()
}

fun Activity.changeLanguage(language: String, isFirstTime: Boolean = true) {
    val locale = Locale(language)
    val resources = resources
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)
    if (!isFirstTime) recreate()
}