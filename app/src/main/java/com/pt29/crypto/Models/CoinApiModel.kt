package com.pt29.crypto.Models

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class CoinApiModel : RealmObject, Parcelable {


    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("symbol")
    @Expose
    var symbol: String? = null
    @SerializedName("rank")
    @Expose
    var rank: String? = null
    @SerializedName("price_usd")
    @Expose
    var priceUsd: String? = null
    @SerializedName("price_btc")
    @Expose
    var priceBtc: String? = null
    @SerializedName("24h_volume_usd")
    @Expose
    var _24hVolumeUsd: String? = null
    @SerializedName("market_cap_usd")
    @Expose
    var marketCapUsd: String? = null
    @SerializedName("available_supply")
    @Expose
    var availableSupply: String? = null
    @SerializedName("total_supply")
    @Expose
    var totalSupply: String? = null
    @SerializedName("max_supply")
    @Expose
    var maxSupply: String? = null
    @SerializedName("percent_change_1h")
    @Expose
    var percentChange1h: String? = null
    @SerializedName("percent_change_24h")
    @Expose
    var percentChange24h: String? = null
    @SerializedName("percent_change_7d")
    @Expose
    var percentChange7d: String? = null
    @SerializedName("last_updated")
    @Expose
    var lastUpdated: String? = null

    var favorite: Boolean? = null

    var minAlert: String? = null

    var maxAlert: String? = null

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(String::class.java.classLoader) as String?
        this.name = `in`.readValue(String::class.java.classLoader) as String?
        this.symbol = `in`.readValue(String::class.java.classLoader) as String?
        this.rank = `in`.readValue(String::class.java.classLoader) as String?
        this.priceUsd = `in`.readValue(String::class.java.classLoader) as String?
        this.priceBtc = `in`.readValue(String::class.java.classLoader) as String?
        this._24hVolumeUsd = (`in`.readValue((String::class.java.getClassLoader())) as String?)
        this.marketCapUsd = `in`.readValue(String::class.java.classLoader) as String?
        this.availableSupply = `in`.readValue(String::class.java.classLoader) as String?
        this.totalSupply = `in`.readValue(String::class.java.classLoader) as String?
        this.maxSupply = `in`.readValue(String::class.java.classLoader) as String?
        this.percentChange1h = `in`.readValue(String::class.java.classLoader) as String?
        this.percentChange24h = `in`.readValue(String::class.java.classLoader) as String?
        this.percentChange7d = `in`.readValue(String::class.java.classLoader) as String?
        this.lastUpdated = `in`.readValue(String::class.java.classLoader) as String?
        this.favorite = `in`.readValue(Boolean::class.java.classLoader) as Boolean?
        this.minAlert = `in`.readValue(String::class.java.classLoader) as String?
        this.maxAlert = `in`.readValue(String::class.java.classLoader) as String?
    }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param percentChange24h
     * @param symbol
     * @param marketCapUsd
     * @param maxAlert
     * @param minAlert
     * @param lastUpdated
     * @param percentChange1h
     * @param favorite
     * @param _24hVolumeUsd
     * @param availableSupply
     * @param id
     * @param rank
     * @param maxSupply
     * @param percentChange7d
     * @param name
     * @param priceBtc
     * @param totalSupply
     * @param priceUsd
     */
    constructor(id: String, name: String, symbol: String, rank: String, priceUsd: String, priceBtc: String, _24hVolumeUsd: String, marketCapUsd: String, availableSupply: String, totalSupply: String, maxSupply: String, percentChange1h: String, percentChange24h: String, percentChange7d: String, lastUpdated: String, favorite: Boolean?, minAlert: String,maxAlert:String) : super() {
        this.id = id
        this.name = name
        this.symbol = symbol
        this.rank = rank
        this.priceUsd = priceUsd
        this.priceBtc = priceBtc
        this._24hVolumeUsd = _24hVolumeUsd
        this.marketCapUsd = marketCapUsd
        this.availableSupply = availableSupply
        this.totalSupply = totalSupply
        this.maxSupply = maxSupply
        this.percentChange1h = percentChange1h
        this.percentChange24h = percentChange24h
        this.percentChange7d = percentChange7d
        this.lastUpdated = lastUpdated
        this.favorite = favorite
        this.minAlert = minAlert
        this.minAlert = maxAlert
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(name)
        dest.writeValue(symbol)
        dest.writeValue(rank)
        dest.writeValue(priceUsd)
        dest.writeValue(priceBtc)
        dest.writeValue(_24hVolumeUsd)
        dest.writeValue(marketCapUsd)
        dest.writeValue(availableSupply)
        dest.writeValue(totalSupply)
        dest.writeValue(maxSupply)
        dest.writeValue(percentChange1h)
        dest.writeValue(percentChange24h)
        dest.writeValue(percentChange7d)
        dest.writeValue(lastUpdated)
        dest.writeValue(favorite)
        dest.writeValue(minAlert)
        dest.writeValue(maxAlert)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CoinApiModel> = object : Creator<CoinApiModel> {


            override fun createFromParcel(`in`: Parcel): CoinApiModel {
                return CoinApiModel(`in`)
            }

            override fun newArray(size: Int): Array<CoinApiModel?> {
                return arrayOfNulls(size)
            }

        }
    }

}
