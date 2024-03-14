package com.ft.tasbhi_widget

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import com.ft.tasbhi_widget.receiver.TimeChangedReceiver
import com.orhanobut.hawk.Hawk

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeHawk()
        registerTimeChangedReceiver()
    }

    private fun registerTimeChangedReceiver() {
        registerReceiver(TimeChangedReceiver(), IntentFilter(Intent.ACTION_TIME_TICK))
    }

    private fun initializeHawk() {
        Hawk.init(applicationContext).build()
    }
}