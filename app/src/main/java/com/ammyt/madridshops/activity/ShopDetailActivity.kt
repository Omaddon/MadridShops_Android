package com.ammyt.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ammyt.domain.model.Shop
import com.ammyt.madridshops.R
import com.ammyt.madridshops.utils.INTENT_SHOP_DETAIL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        // TODO comprobar idioma a mostrar
        shop_name.text =  shop.name
        shop_description.text = shop.description_es
        shop_address.text = shop.address
        shop_hours.text = shop.openingHours_es

        Picasso
                .with(this)
                .load(shop.imageURL)
                .placeholder(R.drawable.no_image)
                .into(shop_image)

        val googleMap_url =
                "https://maps.googleapis.com/maps/api/staticmap?%25&size=320x220&scale=2&markers=" + shop.latitude + "," + shop.longitude

        Picasso
                .with(this)
                .load(googleMap_url)
                .placeholder(R.drawable.no_image)
                .into(shop_google_map)
    }
}
