package com.pt29.crypto.Adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pt29.crypto.Activities.MainActivity
import com.pt29.crypto.Models.NewsModel
import com.pt29.crypto.R
import com.pt29.crypto.Utils.GlideApp
import me.grantland.widget.AutofitHelper
import java.util.*

/**
 * Created by parham on 5/31/2017.
 */


class NewsListAdapter(val activity: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var newsList: MutableList<NewsModel>? = null

    val isEmpty: Boolean
        get() = itemCount == 0

    init {
        newsList = ArrayList()

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
        val v1 = inflater.inflate(R.layout.card_news_item, parent, false)
        viewHolder = CoinsVH(v1)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val News = newsList!![position]

        val newsVH = holder as CoinsVH


        newsVH.card_news_title.text = News.newsTitle
        newsVH.card_news_text.text = News.newsText


        AutofitHelper.create(newsVH.card_news_title)
//        AutofitHelper.create(newsVH.card_txt_percent)


        GlideApp.with(newsVH.card_news_img.context).load(News.newsImg)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(newsVH.card_news_img)








        holder.itemView.setOnClickListener {
            //            News = newsList!![position]
//            startTransition(newsVH, News)
        }

    }

//    private fun startTransition(newsVH: CoinsVH, coinApiModel: NewsModel) {
//
//        val intent = Intent(activity, DetailsActivity::class.java)
//        intent.putExtra("Coin", coinApiModel);
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, newsVH.card_img_coin_logo, "profile");
//        activity.startActivity(intent, options.toBundle())
//
//    }

    override fun getItemCount(): Int {
        return if (newsList == null) 0 else newsList!!.size
    }


    fun add(r: NewsModel) {
        newsList!!.add(r)
        notifyItemInserted(newsList!!.size - 1)
    }

    private fun addAll(list: List<NewsModel>) {
        for (item in list) {
            add(item)
        }
    }

    fun remove(r: NewsModel) {
        val position = newsList!!.indexOf(r)
        if (position > -1) {
            newsList!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }


    fun getItem(position: Int): NewsModel {
        return newsList!![position]
    }


    private inner class CoinsVH constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card_news_img: ImageView
        val card_news_title: TextView
        val card_news_text: TextView


        init {
            card_news_img = itemView.findViewById<View>(R.id.card_news_img) as ImageView
            card_news_title = itemView.findViewById<View>(R.id.card_news_title) as TextView
            card_news_text = itemView.findViewById<View>(R.id.card_news_text) as TextView
        }
    }


}











