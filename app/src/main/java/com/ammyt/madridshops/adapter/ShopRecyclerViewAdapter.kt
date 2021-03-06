package com.ammyt.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ammyt.domain.model.Shop
import com.ammyt.domain.model.Shops
import com.ammyt.madridshops.R
import com.ammyt.madridshops.utils.getOpeningHours
import com.squareup.picasso.Picasso

class ShopRecyclerViewAdapter(val shopList: Shops?) :
        RecyclerView.Adapter<ShopRecyclerViewAdapter.ShopListViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onBindViewHolder(holder: ShopListViewHolder?, position: Int) {
        shopList?.let {
            holder?.bindShop(shopList.get(position))
        }
    }

    override fun getItemCount(): Int {
        shopList?.let {
            return it.count()
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_item_list, parent, false)
        view.setOnClickListener(onClickListener)

        return ShopListViewHolder(view)
    }


    inner class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val shopImage = itemView.findViewById<ImageView>(R.id.item_logo)
        val shopName = itemView.findViewById<TextView>(R.id.item_name)
        val shopHours = itemView.findViewById<TextView>(R.id.item_hours)

        fun bindShop(shop: Shop) {
            shopName.text = shop.name

            //Picasso.with(itemView.context).setIndicatorsEnabled(true)
            //Picasso.with(itemView.context).isLoggingEnabled = true

            Picasso
                    .with(itemView.context)
                    .load(shop.logoURL)
                    .placeholder(R.drawable.no_image)
                    .into(shopImage)

            shopHours.text = getOpeningHours(shop)
        }
    }
}