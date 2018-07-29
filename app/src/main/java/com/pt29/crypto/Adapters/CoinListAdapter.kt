package com.pt29.crypto.Adapters


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.Utils
import com.pt29.crypto.Activities.DetailsActivity
import com.pt29.crypto.Activities.MainActivity
import com.pt29.crypto.Models.CoinApiModel
import com.pt29.crypto.R
import com.pt29.crypto.Utils.GlideApp
import me.grantland.widget.AutofitHelper
import java.util.*

/**
 * Created by parham on 5/31/2017.
 */


class CoinListAdapter(val activity: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var coinsList: MutableList<CoinApiModel>? = null

    val isEmpty: Boolean
        get() = itemCount == 0

    init {
        coinsList = ArrayList()

    }

    fun setKasbokars(coinsList: MutableList<CoinApiModel>) {

        this.coinsList = coinsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder?
        val inflater = LayoutInflater.from(parent.context)
        parent.setMotionEventSplittingEnabled(false)
        viewHolder = getViewHolder(parent, inflater)
        return viewHolder
    }

    private fun getViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val v1 = inflater.inflate(R.layout.card_coin, parent, false)
        viewHolder = CoinsVH(v1)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var Coin = coinsList!![position]

        fun rand(): Float {
            val random = Random()
            return 29 * random.nextFloat()
        }

        val coinsVH = holder as CoinsVH


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
        dataset.lineWidth = 1.8f
        dataset.setDrawCircles(false)
        dataset.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataset.color = ContextCompat.getColor(coinsVH.card_chart.context, R.color.card_chart_color)

        dataset.setDrawFilled(true)
        if (Utils.getSDKInt() >= 18) {
            val drawable = ContextCompat.getDrawable(coinsVH.card_chart.context, R.drawable.multigrad)
            dataset.fillDrawable = drawable
        } else {
            dataset.fillColor = Color.BLACK
        }

        val data = LineData(dataset)
        coinsVH.card_chart.data = data
        coinsVH.card_chart.description.isEnabled = false
        coinsVH.card_chart.axisLeft.isEnabled = false
        coinsVH.card_chart.axisRight.isEnabled = false
        coinsVH.card_chart.xAxis.isEnabled = false
        coinsVH.card_chart.legend.isEnabled = false
        coinsVH.card_chart.isDragEnabled = false
        coinsVH.card_chart.setScaleEnabled(true)
        coinsVH.card_chart.isDoubleTapToZoomEnabled = false
        coinsVH.card_chart.setPinchZoom(false)
        coinsVH.card_chart.setDrawGridBackground(false)
        coinsVH.card_chart.setDrawBorders(false)
        coinsVH.card_chart.isAutoScaleMinMaxEnabled = true
        coinsVH.card_chart.data.isHighlightEnabled = false
//        coinsVH.card_chart.animateY(1000, Easing.EasingOption.EaseInOutExpo)


        coinsVH.card_txt_name.text = Coin.name
        coinsVH.card_txt_symbol.text = Coin.symbol


        coinsVH.card_txt_percent.text = (String.format("%s%%", Coin.percentChange1h))
        coinsVH.card_txt_price.text = (String.format("$ %s", Coin.priceUsd))


        AutofitHelper.create(coinsVH.card_txt_name)
        AutofitHelper.create(coinsVH.card_txt_percent)
        AutofitHelper.create(coinsVH.card_txt_price)
        AutofitHelper.create(coinsVH.card_txt_symbol)


        GlideApp.with(coinsVH.card_chart.context).load(
                coinsVH.card_txt_symbol.context.getResources().getIdentifier(Coin.symbol!!.toLowerCase(),
                        "drawable", coinsVH.card_txt_symbol.context.getPackageName()))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(GlideApp.with(coinsVH.card_chart.context)
                        .load(String.format("https://blockfolio.com/coin_images/%s.png", Coin.symbol!!.toLowerCase())))
                .into(coinsVH.card_img_coin_logo)


        if (Coin.percentChange1h != null && (Coin.percentChange1h!![0].compareTo('-')) == 0) {

            GlideApp.with(coinsVH.card_chart.context)
                    .load(R.drawable.trendingdown)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(coinsVH.card_img_trending)
            coinsVH.card_txt_percent.setTextColor(ContextCompat.getColor(coinsVH.card_img_coin_logo.context, R.color.red))

        } else {

            GlideApp.with(coinsVH.card_chart.context)
                    .load(R.drawable.trendingup)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(coinsVH.card_img_trending)
            coinsVH.card_txt_percent.setTextColor(ContextCompat.getColor(coinsVH.card_img_coin_logo.context, R.color.green))

        }



        coinsVH.card_chart.setOnLongClickListener {
        Toast.makeText(it.context,"long",Toast.LENGTH_LONG).show()
        }
        coinsVH.card_chart.setOnClickListener {
            Coin = coinsList!![position]
            startTransition(coinsVH, Coin)
        }

        holder.itemView.setOnClickListener {
            Coin = coinsList!![position]
            startTransition(coinsVH, Coin)
        }

    }

    private fun startTransition(coinsVH: CoinsVH, coinApiModel: CoinApiModel) {

        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("Coin", coinApiModel);
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, coinsVH.card_img_coin_logo, "profile");
        activity.startActivity(intent, options.toBundle())

    }

    override fun getItemCount(): Int {
        return if (coinsList == null) 0 else coinsList!!.size
    }


    fun add(r: CoinApiModel) {
        coinsList!!.add(r)
        notifyItemInserted(coinsList!!.size - 1)
    }

    private fun addAll(list: List<CoinApiModel>) {
        for (item in list) {
            add(item)
        }
    }

    fun remove(r: CoinApiModel) {
        val position = coinsList!!.indexOf(r)
        if (position > -1) {
            coinsList!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }


    fun getItem(position: Int): CoinApiModel {
        return coinsList!![position]
    }

//    override fun getItemViewType(position: Int): Int {
//        return position
//    }

    private inner class CoinsVH constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card_img_coin_logo: ImageView
        val card_txt_name: TextView
        val card_txt_symbol: TextView
        val card_txt_price: TextView
        val card_txt_percent: TextView
        val card_img_trending: ImageView
        val card_chart: LineChart


        init {
            card_img_coin_logo = itemView.findViewById<View>(R.id.card_img_coin_logo) as ImageView
            card_txt_name = itemView.findViewById<View>(R.id.card_txt_name) as TextView
            card_txt_price = itemView.findViewById<View>(R.id.card_txt_price) as TextView
            card_txt_percent = itemView.findViewById<View>(R.id.card_txt_percent) as TextView
            card_txt_symbol = itemView.findViewById<View>(R.id.card_txt_symbol) as TextView
            card_img_trending = itemView.findViewById<View>(R.id.card_img_trending) as ImageView
            card_chart = itemView.findViewById<View>(R.id.card_chart) as LineChart
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val anim = AlphaAnimation(1.0f, 0.6f)
            anim.duration = 200
            val coinsVH = holder as CoinsVH
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                if (key == "price") {

                    coinsVH.card_txt_price.startAnimation(anim)
                    coinsVH.card_txt_price.text = (String.format("$ %s", coinsList!![position].priceUsd))
                    AutofitHelper.create(coinsVH.card_txt_price)

                }
                if (key == "percent") {

                    coinsVH.card_txt_percent.startAnimation(anim)
                    coinsVH.card_txt_percent.text = (String.format("%s%%", coinsList!![position].percentChange1h))
                    if ((coinsList!![position].percentChange1h!![0].compareTo('-')) == 0) {

                        GlideApp.with(coinsVH.card_chart.context)
                                .load(R.drawable.trendingdown)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(coinsVH.card_img_trending)
                        coinsVH.card_txt_percent.setTextColor(ContextCompat.getColor(coinsVH.card_img_coin_logo.context, R.color.red))

                    } else {

                        GlideApp.with(coinsVH.card_chart.context)
                                .load(R.drawable.trendingup)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(coinsVH.card_img_trending)
                        coinsVH.card_txt_percent.setTextColor(ContextCompat.getColor(coinsVH.card_img_coin_logo.context, R.color.green))

                    }
                    AutofitHelper.create(coinsVH.card_txt_percent)
                }
            }
        }
    }

    fun onNewData(newData: List<CoinApiModel>) {


        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(newData, coinsList!!))
        diffResult.dispatchUpdatesTo(this)
        this.coinsList!!.clear()
        this.coinsList!!.addAll(newData)

    }


}











