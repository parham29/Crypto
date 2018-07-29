package com.pt29.crypto.NetworkInterface

import com.pt29.crypto.Models.CoinApiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by parham on 2/19/2018.
 */
interface GetAllCoinsInterface {
    @GET("ticker/")
    fun getAllCoins(@Query("limit") limit: Int,@Query("start") start: Int): Observable<List<CoinApiModel>>
}