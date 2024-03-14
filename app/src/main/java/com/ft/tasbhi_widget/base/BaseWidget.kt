package com.ft.tasbhi_widget.base

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class BaseWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        onAfterUpdate(
            context = context,
            appWidgetManager = appWidgetManager,
            appWidgetIds = appWidgetIds
        )
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        context?.let {
            appWidgetManager?.let {
                onAfterOptionChanged(
                    context = context,
                    appWidgetManager = appWidgetManager,
                    appWidgetId = appWidgetId
                )
            }
        }
    }

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        super.onReceive(context, intent)
        onAfterReceive(
            context = context,
            intent = intent
        )
    }
    protected open fun onAfterUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
    }
    protected open fun onAfterReceive(
        context: Context,
        intent: Intent
    ) {
    }
    protected open fun onAfterOptionChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
    }
}