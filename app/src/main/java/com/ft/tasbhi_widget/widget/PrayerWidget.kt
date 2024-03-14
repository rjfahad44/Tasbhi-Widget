package com.ft.tasbhi_widget.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.ft.tasbhi_widget.MainActivity
import com.ft.tasbhi_widget.R
import com.ft.tasbhi_widget.base.BaseWidget
import com.ft.tasbhi_widget.utils.Constants
import com.ft.tasbhi_widget.utils.Constants.CHECK_DAY
import com.ft.tasbhi_widget.utils.Constants.COLOR
import com.ft.tasbhi_widget.utils.Constants.LANGUAGE
import com.ft.tasbhi_widget.utils.Constants.RESET_SALAVAT
import com.ft.tasbhi_widget.utils.Constants.SALAVAT
import com.ft.tasbhi_widget.utils.HawkManager
import com.ft.tasbhi_widget.utils.getTodayName

class PrayerWidget : BaseWidget() {
    override fun onAfterUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context = context,
                appWidgetManager = appWidgetManager,
                appWidgetId = appWidgetId
            )
        }
    }

    override fun onAfterOptionChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        updateAppWidget(
            context = context,
            appWidgetManager = appWidgetManager,
            appWidgetId = appWidgetId
        )
    }

    override fun onAfterReceive(context: Context, intent: Intent) {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_salavat)
        when (intent.action) {
            SALAVAT -> {
                val todayName = getTodayName()
                val savedDay = HawkManager.getSalavatDay()
                if (todayName != savedDay) {
                    HawkManager.saveSalavat(salavat = 0)
                    HawkManager.saveSalavatDay(day = getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvSalavatCounter,
                        HawkManager.getSalavat().toString()
                    )
                    remoteViews.setTextViewText(R.id.tvSalavatDay, getTodayName())
                } else {
                    remoteViews.setTextViewText(
                        R.id.tvSalavatCounter,
                        HawkManager.increaseSalavat().toString()
                    )
                }
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, PrayerWidget::class.java),
                    remoteViews
                )
            }

            RESET_SALAVAT -> {
                remoteViews.setTextViewText(
                    R.id.tvSalavatCounter,
                    HawkManager.getSalavat().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, PrayerWidget::class.java),
                    remoteViews
                )
            }

            COLOR -> {
                remoteViews.setTextColor(
                    R.id.tvSalavatTitle,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, PrayerWidget::class.java),
                    remoteViews
                )
            }

            LANGUAGE -> {
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, TasbihatWidget::class.java),
                    remoteViews
                )
            }

            CHECK_DAY -> {
                val todayName = getTodayName()
                val savedDay = HawkManager.getSalavatDay()
                if (todayName != savedDay) {
                    HawkManager.saveSalavat(salavat = 0)
                    HawkManager.saveSalavatDay(day = getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvSalavatCounter,
                        HawkManager.getSalavat().toString()
                    )
                    remoteViews.setTextViewText(R.id.tvSalavatDay, getTodayName())
                    AppWidgetManager.getInstance(context).updateAppWidget(
                        ComponentName(context, PrayerWidget::class.java),
                        remoteViews
                    )
                }
            }
        }
    }
    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_salavat)
        HawkManager.saveSalavatDay(day = getTodayName())
        views.setTextViewText(R.id.tvSalavatDay, getTodayName())
        views.setTextViewText(R.id.tvSalavatCounter, HawkManager.getSalavat().toString())
        views.setTextColor(
            R.id.tvSalavatTitle,
            context.resources.getColor(HawkManager.getTextColor().color)
        )
        views.setOnClickPendingIntent(
            R.id.tvSalavatCounter,
            updateSalavatIntent(
                context = context,
                action = SALAVAT
            )
        )
        views.setOnClickPendingIntent(
            R.id.tvSalavatDay,
            openHomeActivityIntent(
                context = context,
                appWidgetId = appWidgetId
            )
        )
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
    private fun openHomeActivityIntent(
        context: Context,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
    private fun updateSalavatIntent(
        context: Context?,
        action: String?
    ): PendingIntent? {
        val intent = Intent(context, PrayerWidget::class.java)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}