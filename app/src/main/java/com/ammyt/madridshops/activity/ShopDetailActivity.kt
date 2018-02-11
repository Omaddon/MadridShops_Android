package com.ammyt.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ammyt.domain.model.Shop
import com.ammyt.madridshops.R
import com.ammyt.madridshops.utils.GOOGLE_MAP_URL
import com.ammyt.madridshops.utils.INTENT_SHOP_DETAIL
import com.ammyt.madridshops.utils.getDescription
import com.ammyt.madridshops.utils.getOpeningHours
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shop_detail.*
import java.util.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        shop_name.text =  shop.name
        shop_description.text = getDescription(shop)
        shop_address.text = shop.address
        shop_hours.text = getOpeningHours(shop)

        Picasso
                .with(this)
                .load(shop.imageURL)
                .placeholder(R.drawable.no_image)
                .into(shop_image)

        val googleMapUrl = GOOGLE_MAP_URL + shop.latitude + "," + shop.longitude

        Picasso
                .with(this)
                .load(googleMapUrl)
                .placeholder(R.drawable.no_image)
                .into(shop_google_map)
    }
}
