package com.pt29.crypto.Network

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pt29.crypto.Activities.MainActivity
import com.pt29.crypto.Models.CoinApiModel
import com.pt29.crypto.NetworkInterface.GetAllCoinsInterface
import com.pt29.crypto.NetworkInterface.GetCoinInterface
import com.pt29.crypto.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

/**
 * Created by parham on 2/16/2018.
 */

class NetworkManager {
    var dbManagment = com.pt29.crypto.DBManager.dbManagment()

    fun getAllFavoritesData() {

        for (item in dbManagment.getAllFavorites(true)){
            getCoin(item.id)

        }


       // getAllCoins()
    }
    fun getAllCoins() {
        val getCoinInterface = ApiService.client!!.create(GetAllCoinsInterface::class.java)
        Log.i("LOG29","response")
        getCoinInterface.getAllCoins(1600,0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            for (item in response){
                                dbManagment.updateCoin(item)

                            }

                        }, { error ->
                    Log.e("Error", error.localizedMessage)
                }
                )



    }

    fun getCoin(coinname: String?) {
        val getCoinInterface = ApiService.client!!.create(GetCoinInterface::class.java)

        getCoinInterface.getCoin(coinname!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->

                            for (item in response) {
                                dbManagment.updateCoin(item)
                            }

                        }, { error ->
                                Log.e("Error", error.localizedMessage)
                }
                )

//   val call = getCoinInterface.getCoin(coinname!!)
//        call.enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    val collectionType = object : TypeToken<Collection<CoinApiModel>>() {
//                    }.type
//                    val json = response.body()
//                    val coins = Gson().fromJson<Collection<CoinApiModel>>(json, collectionType)
//
//                    for (item in coins) {
//                        dbManagment.updateCoin(item)
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                val notification = NotificationCompat.Builder(context)
//                        .setContentTitle("no connection")
//                        .setContentText("please check your network connection")
//                        .setAutoCancel(false)
//                        .setSmallIcon(R.drawable.bitcoin)
//                        .setShowWhen(true)
//                        .setColor(Color.BLUE)
//                        .setLocalOnly(true)
//                        .build()
////
////                NotificationManagerCompat.from(context).notify(Random().nextInt(), notification)
//                call.cancel()
//            }
//        })

    }
}
