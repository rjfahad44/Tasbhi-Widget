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
import com.ft.tasbhi_widget.utils.Constants.RESET_ZEKR
import com.ft.tasbhi_widget.utils.Constants.ZEKR
import com.ft.tasbhi_widget.utils.HawkManager
import com.ft.tasbhi_widget.utils.getTodayName
import com.ft.tasbhi_widget.utils.getTodayZekr

class ZekrWidget : BaseWidget() {
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
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_zekr)
        when (intent.action) {
            ZEKR -> {
                val todayName = getTodayName()
                val savedDay = HawkManager.getZekrDay()
                if (todayName != savedDay) {
                    HawkManager.saveZekr(zekr = 0)
                    HawkManager.saveZekrDay(day = getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvZekrCounter,
                        HawkManager.getSalavat().toString()
                    )
                    remoteViews.setTextViewText(R.id.tvZekrDay, getTodayName())
                    remoteViews.setTextViewText(R.id.tvTodayZekr, getTodayZekr())
                } else {
                    remoteViews.setTextViewText(
                        R.id.tvZekrCounter,
                        HawkManager.increaseZekr().toString()
                    )
                }
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, ZekrWidget::class.java),
                    remoteViews
                )
            }

            RESET_ZEKR -> {
                remoteViews.setTextViewText(
                    R.id.tvZekrCounter,
                    HawkManager.getZekr().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, ZekrWidget::class.java),
                    remoteViews
                )
            }

            COLOR -> {
                remoteViews.setTextColor(
                    R.id.tvTodayZekrTitle,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                remoteViews.setTextColor(
                    R.id.tvTodayZekr,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, ZekrWidget::class.java),
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
                val savedDay = HawkManager.getZekrDay()
                if (todayName != savedDay) {
                    HawkManager.saveZekr(zekr = 0)
                    HawkManager.saveZekrDay(day = getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvZekrCounter,
                        HawkManager.getSalavat().toString()
                    )
                    remoteViews.setTextViewText(R.id.tvZekrDay, getTodayName())
                    remoteViews.setTextViewText(R.id.tvTodayZekr, getTodayZekr())
                    AppWidgetManager.getInstance(context).updateAppWidget(
                        ComponentName(context, ZekrWidget::class.java),
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
        val views = RemoteViews(context.packageName, R.layout.widget_zekr)
        HawkManager.saveZekrDay(day = getTodayName())
        views.setTextViewText(R.id.tvZekrDay, getTodayName())
        views.setTextViewText(R.id.tvZekrCounter, HawkManager.getZekr().toString())
        views.setTextViewText(R.id.tvTodayZekr, getTodayZekr())
        views.setTextColor(
            R.id.tvTodayZekrTitle,
            context.resources.getColor(HawkManager.getTextColor().color)
        )
        views.setTextColor(
            R.id.tvTodayZekr,
            context.resources.getColor(HawkManager.getTextColor().color)
        )
        views.setOnClickPendingIntent(
            R.id.tvZekrCounter,
            updateZekrIntent(
                context = context,
                action = ZEKR,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.tvZekrDay,
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

    private fun updateZekrIntent(
        context: Context?,
        action: String?,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, ZekrWidget::class.java)
        intent.action = action
        intent.putExtra("id", appWidgetId)
        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}