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
import kotlinx.android.synthetic.main.activity_item_detail.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        item_name.text =  shop.name
        item_description.text = getDescription(shop)
        item_address.text = shop.address
        item_hours.text = getOpeningHours(shop)

        Picasso
                .with(this)
                .load(shop.imageURL)
                .placeholder(R.drawable.no_image)
                .into(item_image)

        val googleMapUrl = GOOGLE_MAP_URL + shop.latitude + "," + shop.longitude

        Picasso
                .with(this)
                .load(googleMapUrl)
                .placeholder(R.drawable.no_image)
                .into(item_google_map)
    }
}
