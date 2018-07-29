package com.pt29.crypto.Activities

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.pt29.crypto.DBManager.dbManagment
import com.pt29.crypto.R
import com.pt29.crypto.Widget.NewAppWidget
import kotlinx.android.synthetic.main.activity_widget_configure.*






class WidgetConfigureActivity : AppCompatActivity() {
    val context: Context = this
    var configOkButton: Button? = null
    var mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResult(RESULT_CANCELED);
        setContentView(R.layout.activity_widget_configure)


        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID)
        }

        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }



        btn_config.setOnClickListener(View.OnClickListener {

            dbManagment().addWidget(mAppWidgetId,"bitcoin")
            val resultValue = Intent()
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId)

            val intent2 = Intent(context, NewAppWidget::class.java)
            intent2.action = "android.appwidget.action.APPWIDGET_UPDATE"
            val ids = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetIds(ComponentName(context.getApplicationContext(), NewAppWidget::class.java))
            intent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            context.sendBroadcast(intent2)




            setResult(RESULT_OK, resultValue)






            finish();
        })


    }


}
