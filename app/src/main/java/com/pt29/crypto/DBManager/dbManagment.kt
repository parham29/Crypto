package com.pt29.crypto.DBManager

import android.os.AsyncTask
import android.util.Log
import com.pt29.crypto.Activities.adapter
import com.pt29.crypto.Models.CoinApiModel
import com.pt29.crypto.Models.WidgetModel
import com.pt29.crypto.Network.NetworkManager
import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where


/**
 * Created by parham on 2/16/2018.
 */


class dbManagment {

    var coinList:List<CoinApiModel>? = null
    fun getRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    fun getAllFavorites(sort: Boolean): MutableList<CoinApiModel> {
        val realm = getRealm()

        if (sort) {
            val query = realm.where<CoinApiModel>().equalTo("favorite", true).sort("id")
            val arrayListOfUnmanagedObjects = realm.copyFromRealm(query.findAll())
            return arrayListOfUnmanagedObjects
        } else {
            val query = realm.where<CoinApiModel>().equalTo("favorite", true).sort("percentChange1h",Sort.DESCENDING)
            val arrayListOfUnmanagedObjects = realm.copyFromRealm(query.findAll())
            return arrayListOfUnmanagedObjects
        }


    }




    fun getAll(): MutableList<CoinApiModel> {
        val realm = getRealm()
        val query = realm.where<CoinApiModel>().notEqualTo("favorite", true)
        val arrayListOfUnmanagedObjects = realm.copyFromRealm(query.findAll())
        return arrayListOfUnmanagedObjects

    }

    fun addToFavorites(coinId: String) {
        getRealm().executeTransactionAsync({ bgRealm ->
            val coin = bgRealm.where(CoinApiModel::class.java)
                    .equalTo("id", coinId)
                    .findFirst()
            if (coin != null) {
                coin.favorite = true
            }

        }, {
            NetworkManager().getCoin(coinId)


        }) {

            Log.i("LOG2", "not sageed")
        }


    }

    fun removeFromFavorites(coinId: String) {
        getRealm().executeTransactionAsync({ bgRealm ->
            val coin = bgRealm.where(CoinApiModel::class.java)
                    .equalTo("id", coinId)
                    .findFirst()
            if (coin != null) {
                coin.favorite = false
            }

        }, {



        }) {


        }


    }





    fun updateCoin(coinApi: CoinApiModel) {
        getRealm().executeTransactionAsync({ bgRealm ->
            val coin = bgRealm.where(CoinApiModel::class.java)
                    .equalTo("id", coinApi.id)
                    .findFirst()
            if (coin != null) {
                coin.priceUsd = coinApi.priceUsd
                coin.percentChange1h = coinApi.percentChange1h
            }

        }, {
             adapter!!.onNewData(getAllFavorites(true))
        }) {

            Log.i("LOG2", "not sageed")
        }


    }


    fun addWidget(appWidgetId: Int, coinID: String) {
        getRealm().executeTransactionAsync({ bgRealm ->
            val widget = bgRealm.createObject(WidgetModel::class.java)
            widget.appWidgetId = appWidgetId
            widget.coinID = coinID

        }, {
            Log.i("LOG232", "Widget added: "+appWidgetId+" "+coinID)


        }) {


        }


    }

    fun getWidget(appWidgetId: Int):String {
        val realm = getRealm()
        val query = realm.where<WidgetModel>().equalTo("appWidgetId", appWidgetId)

        return query.findFirst()?.coinID?:"error"

    }




}
