package com.pt29.crypto.NetworkInterface

import com.pt29.crypto.Models.CoinApiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by parham on 2/12/2018.
 */

interface GetCoinInterface {
    @GET("ticker/{coin}")
    fun getCoin(@Path("coin") coin: String): Observable<List<CoinApiModel>>
}