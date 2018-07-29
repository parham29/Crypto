package com.pt29.crypto.Activities

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.Utils
import com.pt29.crypto.Models.CoinApiModel
import com.pt29.crypto.R
import com.pt29.crypto.Utils.GlideApp
import kotlinx.android.synthetic.main.activity_details.*
import me.grantland.widget.AutofitHelper
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val coin = intent.extras!!.getParcelable<CoinApiModel>("Coin")
        txt_details_name.text = coin.name
        txt_details_price.text = (String.format("%s ریال", coin.priceUsd))
        txt_details_percent_1h.text = (String.format("%s%%", coin.percentChange1h))
        txt_details_market_cap_value.text = (String.format("$%s", "144,459,462,992,222"))
        txt_details_usd_price_value.text = (String.format("$%s", coin.priceUsd))
        txt_details_volume_value.text = (String.format("$%s", "144,459,462,992,222"))

        AutofitHelper.create(txt_details_market_cap_value)
        AutofitHelper.create(txt_details_usd_price_value)
        AutofitHelper.create(txt_details_volume_value)
        AutofitHelper.create(txt_details_name)


        GlideApp.with(this).load(R.drawable.backdet).centerInside().override(200, 200).into(img_details_background)

        img_details_back.setOnClickListener(View.OnClickListener {
            supportFinishAfterTransition()

        })

        if (coin.percentChange1h != null && (coin.percentChange1h!![0].compareTo('-')) == 0) {
            txt_details_percent_1h.setTextColor(ContextCompat.getColor(this, R.color.red))
        } else {
            txt_details_percent_1h.setTextColor(ContextCompat.getColor(this, R.color.green))
        }




        GlideApp.with(this).load(
                getResources().getIdentifier(coin.symbol!!.toLowerCase(),
                        "drawable", getPackageName())
        ).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                        this@DetailsActivity.supportStartPostponedEnterTransition()
                        return false
                    }

                })
                .error(GlideApp.with(this)
                        .load(String.format("https://blockfolio.com/coin_images/%s.png", coin.symbol!!.toLowerCase()))).into(img_details_logo)


        fun rand(): Float {
            val random = Random()
            return 29 * random.nextFloat()
        }


        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, rand()))
        entries.add(Entry(2f, rand()))
        entries.add(Entry(3f, rand()))
        entries.add(Entry(4f, rand()))
        entries.add(Entry(5f, rand()))
        entries.add(Entry(6f, rand()))
        entries.add(Entry(7f, rand()))

        val dataset = LineDataSet(entries, "# of Calls")
        dataset.setDrawValues(false)
        dataset.lineWidth = 2f
        dataset.setDrawCircles(false)
        dataset.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataset.color = ContextCompat.getColor(chart_details.context, R.color.card_chart_color)

        dataset.setDrawFilled(true)
        if (Utils.getSDKInt() >= 18) {
            val drawable = ContextCompat.getDrawable(this, R.drawable.multigrad)
            dataset.fillDrawable = drawable
        } else {
            dataset.fillColor = Color.BLACK
        }

        val data = LineData(dataset)
        chart_details.data = data
        chart_details.description.isEnabled = false
        chart_details.axisLeft.isEnabled = true
        chart_details.axisRight.isEnabled = false
        chart_details.xAxis.isEnabled = true
        chart_details.xAxis.position = XAxis.XAxisPosition.BOTTOM

        chart_details.legend.isEnabled = false
        chart_details.isDragEnabled = false
        chart_details.setScaleEnabled(true)
        chart_details.isDoubleTapToZoomEnabled = false
        chart_details.setPinchZoom(false)
        chart_details.setDrawGridBackground(false)
        chart_details.setDrawBorders(false)
        chart_details.isAutoScaleMinMaxEnabled = true
        chart_details.data.isHighlightEnabled = false
        chart_details.animateY(1000, Easing.EasingOption.EaseInOutExpo)


    }


    @Override
    protected override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    override fun onBackPressed() {
        // super.onBackPressed()
        supportFinishAfterTransition()
    }
}
