package com.pt29.crypto.Adapters

import android.os.Bundle
import android.support.v7.util.DiffUtil
import com.pt29.crypto.Models.CoinApiModel

/**
 * Created by parham on 3/7/2018.
 */

class GridDiffCalback(internal var newCoins: List<CoinApiModel>, internal var oldCoins: List<CoinApiModel>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldCoins.size
    }

    override fun getNewListSize(): Int {
        return newCoins.size
    }

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {

        return oldCoins[oldPos].id.equals(newCoins[newPos].id)

    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldCoins[oldPos].priceUsd.equals(newCoins[newPos].priceUsd)
    }

    override fun getChangePayload(oldPos: Int, newPos: Int): Any? {
        val newcoin = newCoins.get(newPos)
        val oldCoin = oldCoins.get(oldPos)

        val diff = Bundle()

        if (!newcoin.priceUsd.equals(oldCoin.priceUsd)) {
            diff.putString("price", newcoin.priceUsd)
        }
        if (!newcoin.percentChange1h.equals(oldCoin.percentChange1h)) {
            diff.putString("percent", newcoin.percentChange1h)
        }
        return if (diff.size() == 0) {
            null
        } else diff
    }

}

