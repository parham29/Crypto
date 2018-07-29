package com.pt29.crypto.Widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.pt29.crypto.DBManager.dbManagment
import com.pt29.crypto.R
import java.util.*
import android.content.Intent




/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }



    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {

            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            val rightNow = Calendar.getInstance()
            val currentHour = rightNow.get(Calendar.HOUR)
            val currentMin = rightNow.get(Calendar.MINUTE)
            views.setTextViewText(R.id.txt_wdg, "time: " + currentHour + ":" + currentMin+ " -- "+dbManagment().getWidget(appWidgetId))
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)

        }
    }
}

