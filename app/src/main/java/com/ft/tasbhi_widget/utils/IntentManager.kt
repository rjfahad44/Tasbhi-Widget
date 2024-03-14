package com.ft.tasbhi_widget.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.ft.tasbhi_widget.R
import com.ft.tasbhi_widget.utils.Constants.CHECK_DAY
import com.ft.tasbhi_widget.utils.Constants.COLOR
import com.ft.tasbhi_widget.utils.Constants.LANGUAGE
import com.ft.tasbhi_widget.utils.Constants.RESET_SALAVAT
import com.ft.tasbhi_widget.utils.Constants.RESET_TASBIHAT
import com.ft.tasbhi_widget.utils.Constants.RESET_ZEKR
import com.ft.tasbhi_widget.widget.PrayerWidget
import com.ft.tasbhi_widget.widget.TasbihatWidget
import com.ft.tasbhi_widget.widget.ZekrWidget

object IntentManager {

    fun rateIntent(context: Context, view: View) {
        try {
            val intent = Intent(Intent.ACTION_EDIT)
            intent.data = Uri.parse(context.resources.getString(R.string.start_link))
            intent.setPackage(context.packageName)
            context.startActivity(intent)
        } catch (e: java.lang.Exception) {
            context.showSnackbar(
                view = view,
                message = context.getString(R.string.star_notice)
            )
        }
    }

    fun shareTextIntent(context: Context, view: View, title: String, description: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, description)
            context.startActivity(Intent.createChooser(intent, title))
        } catch (e: Exception) {
            context.showSnackbar(
                view = view,
                message = context.getString(R.string.share_process_failed)
            )
        }
    }

    fun resetPrayerIntent(context: Context) {
        val intent = Intent(context, PrayerWidget::class.java)
        intent.action = RESET_SALAVAT
        context.sendBroadcast(intent)
    }

    fun resetZekrIntent(context: Context) {
        val intent = Intent(context, ZekrWidget::class.java)
        intent.action = RESET_ZEKR
        context.sendBroadcast(intent)
    }

    fun changeTasbhiLanguageIntent(context: Context) {
        val intent = Intent(context, TasbihatWidget::class.java)
        intent.action = LANGUAGE
        context.sendBroadcast(intent)
    }
    fun changeZikrLanguageIntent(context: Context) {
        val intent = Intent(context, ZekrWidget::class.java)
        intent.action = LANGUAGE
        context.sendBroadcast(intent)
    }
    fun changePrayerLanguageIntent(context: Context) {
        val intent = Intent(context, PrayerWidget::class.java)
        intent.action = LANGUAGE
        context.sendBroadcast(intent)
    }

    fun resetTasbihatIntent(context: Context) {
        val intent = Intent(context, TasbihatWidget::class.java)
        intent.action = RESET_TASBIHAT
        context.sendBroadcast(intent)
    }

    fun changeSalavatColorIntent(context: Context) {
        val intent = Intent(context, PrayerWidget::class.java)
        intent.action = COLOR
        context.sendBroadcast(intent)
    }

    fun changeZekrColorIntent(context: Context) {
        val intent = Intent(context, ZekrWidget::class.java)
        intent.action = COLOR
        context.sendBroadcast(intent)
    }

    fun changeTasbihatColorIntent(context: Context) {
        val intent = Intent(context, TasbihatWidget::class.java)
        intent.action = COLOR
        context.sendBroadcast(intent)
    }

    fun checkSalavatDayIntent(context: Context) {
        val intent = Intent(context, PrayerWidget::class.java)
        intent.action = CHECK_DAY
        context.sendBroadcast(intent)
    }

    fun checkZekrDayIntent(context: Context) {
        val intent = Intent(context, ZekrWidget::class.java)
        intent.action = CHECK_DAY
        context.sendBroadcast(intent)
    }

}