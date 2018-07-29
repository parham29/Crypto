package com.pt29.crypto.Adapters

import android.graphics.Color
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.marcoscg.dialogsheet.DialogSheet
import com.pt29.crypto.Activities.adapter
import com.pt29.crypto.DBManager.dbManagment
import com.pt29.crypto.Models.CoinApiModel
import com.pt29.crypto.R
import com.pt29.crypto.Utils.GlideApp
import me.grantland.widget.AutofitHelper
import java.util.*

/**
 * Created by parham on 3/7/2018.
 */

class GridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var coinsList: MutableList<CoinApiModel>? = null
        private set

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

        viewHolder = getViewHolder(parent, inflater)
        return viewHolder
    }

    private fun getViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val v1 = inflater.inflate(R.layout.card_grid, parent, false)
        viewHolder = CoinsVH(v1)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val Coin = coinsList!![position]
        val coinsVH = holder as CoinsVH

        coinsVH.txt_coin_name_grid.text = Coin.name
        AutofitHelper.create(coinsVH.txt_coin_name_grid)
        GlideApp.with(coinsVH.img_coin_logo_grid.context).load(
                coinsVH.img_coin_logo_grid.context.getResources().getIdentifier(Coin.symbol!!.toLowerCase(),
                        "drawable", coinsVH.img_coin_logo_grid.context.getPackageName())
        )
                .error(GlideApp.with(coinsVH.img_coin_logo_grid.context)
                        .load(String.format("https://blockfolio.com/coin_images/%s.png", Coin.symbol!!.toLowerCase())))
                .into(coinsVH.img_coin_logo_grid)






        holder.itemView.setOnClickListener {
            val dialogSheet = DialogSheet(it.context)
            dialogSheet
                    .setTitle(R.string.delete)
                    .setMessage(String.format(it.context.getString(R.string.sure), Coin.name))
                    .setCancelable(true)
                    .setIcon(it.context.resources.getIdentifier(Coin.symbol!!.toLowerCase(), "drawable", it.context.packageName))
                    .setPositiveButton(R.string.persian_yes) {
                        dbManagment().removeFromFavorites(Coin.id!!)

                        remove(Coin)
                        adapter!!.onNewData(coinsList!!)
                    }
                    .setNegativeButton(R.string.persian_no) {
                        // Your action
                    }
                    .setBackgroundColor(Color.WHITE) // Your custom background color
                    .setButtonsColorRes(R.color.red)  // Default color is accent

                    .show()

        }

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


    private inner class CoinsVH constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_coin_logo_grid: ImageView
        val txt_coin_name_grid: TextView


        init {
            img_coin_logo_grid = itemView.findViewById<View>(R.id.img_coin_logo_grid) as ImageView
            txt_coin_name_grid = itemView.findViewById<View>(R.id.txt_coin_name_grid) as TextView

        }
    }

    //    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>?) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val coinsVH = holder as CoinsVH
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {

                if (key == "price") {
                    // coinsVH.card_txt_price.animateText(coinsList!![position].priceUsd)

                }
                if (key == "percent") {
                    //    coinsVH.card_txt_percent.animateText(coinsList!![position].percentChange1h ?: "")

                }
            }
        }
    }

    fun onNewData(newData: MutableList<CoinApiModel>) {


        val diffResult = DiffUtil.calculateDiff(GridDiffCalback(newData, coinsList!!))
        diffResult.dispatchUpdatesTo(this)
        this.coinsList!!.clear()
        this.coinsList!!.addAll(newData)

    }
}
