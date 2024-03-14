package com.ft.tasbhi_widget.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ft.tasbhi_widget.utils.IntentManager

class TimeChangedReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            IntentManager.apply {
                checkSalavatDayIntent(context = it)
                checkZekrDayIntent(context = it)
            }
        }
    }

}