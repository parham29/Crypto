package com.pt29.crypto.Utils


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.otaliastudios.autocomplete.AutocompletePresenter
import com.otaliastudios.autocomplete.RecyclerViewPresenter
import com.pt29.crypto.DBManager.dbManagment
import com.pt29.crypto.Models.CoinApiModel
import com.pt29.crypto.R
import me.grantland.widget.AutofitHelper
import java.util.*

/**
 * Created by parham on 2/22/2018.
 */

class CoinPresenter(context: Context) : RecyclerViewPresenter<CoinApiModel>(context) {

    private var adapter: Adapter? = null
    private val all = dbManagment().getAll()

    override fun getPopupDimensions(): AutocompletePresenter.PopupDimensions {
        val dims = AutocompletePresenter.PopupDimensions()
        dims.width = ViewGroup.LayoutParams.WRAP_CONTENT
        dims.height = ViewGroup.LayoutParams.WRAP_CONTENT
        return dims
    }

    override fun instantiateAdapter(): RecyclerView.Adapter<*> {
        adapter = Adapter()
        return adapter!!
    }

    fun updateAllList(item: CoinApiModel){
        all.remove(item)

    }


    override fun onQuery(txtquery: CharSequence?) {
        var tq = txtquery

        if (TextUtils.isEmpty(tq)) {
            adapter!!.setData(all)
        } else {
            tq = tq!!.toString().toLowerCase()
            val list = ArrayList<CoinApiModel>()
            for (u in all) {
                if (u.name!!.toLowerCase().contains(tq) || u.symbol!!.toLowerCase().contains(tq)) {
                    list.add(u)
                }
            }
            adapter!!.setData(list)
        }
        adapter!!.notifyDataSetChanged()
    }

    internal inner class Adapter : RecyclerView.Adapter<Adapter.Holder>() {

        private var data: List<CoinApiModel>? = null

        private val isEmpty: Boolean
            get() = data == null || data!!.isEmpty()

        inner class Holder(val root: View) : RecyclerView.ViewHolder(root) {
            val fullname: TextView
            val username: TextView
            val logo: ImageView

            init {
                fullname = root.findViewById<View>(R.id.txt_coin_name_coinauto) as TextView
                username = root.findViewById<View>(R.id.txt_coin_symbol_coinauto) as TextView
                logo = root.findViewById<View>(R.id.img_coin_logo_coinauto) as ImageView
            }
        }

        fun setData(data: List<CoinApiModel>) {
            this.data = data
        }

        override fun getItemCount(): Int {
            return if (isEmpty) 1 else data!!.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(LayoutInflater.from(context).inflate(R.layout.autocompletecoins, parent, false))
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            if (isEmpty) {
                holder.username.text = "ارزی با این نام پیدا نشد."
                holder.fullname.text = "----------------"
                GlideApp.with(context).load(R.drawable.notfound).into(holder.logo)
                holder.root.setOnClickListener(null)
                return
            }
            val user = data!![position]
            holder.fullname.text = user.name
            holder.username.text = user.symbol
            AutofitHelper.create(holder.fullname)
            AutofitHelper.create(holder.username)
            GlideApp.with(context).load(context.resources.getIdentifier(user.symbol!!.toLowerCase(), "drawable", context.packageName))
                    .error(GlideApp.with(holder.logo.context)
                            .load(String.format("https://blockfolio.com/coin_images/%s.png",user.symbol!!.toLowerCase())))
                    .into(holder.logo)
            holder.root.setOnClickListener { dispatchClick(user) }
        }
    }
}
