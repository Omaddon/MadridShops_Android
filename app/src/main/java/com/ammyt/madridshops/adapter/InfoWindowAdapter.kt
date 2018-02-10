package com.ammyt.madridshops.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.ammyt.domain.model.Shop
import com.ammyt.madridshops.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.info_window_adapter.view.*

class InfoWindowAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {


    @SuppressLint("InflateParams")
    override fun getInfoContents(m: Marker): View {
        val view = LayoutInflater.from(context).inflate(R.layout.info_window_adapter, null)

        if (m.tag is Shop) {
            val shop = m.tag as Shop

            view.info_window_name.text = shop.name
            view.info_window_hours.text = shop.openingHours_es
            val shopImage = view.info_window_image

            // TODO refresh image
            Picasso
                    .with(context)
                    .load(shop.logoURL)
                    .placeholder(R.drawable.no_image)
                    .into(shopImage)
        }

        return view

    }

    override fun getInfoWindow(m: Marker): View? {
        return null
    }
}