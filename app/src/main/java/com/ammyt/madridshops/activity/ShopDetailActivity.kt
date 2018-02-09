package com.ammyt.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ammyt.domain.model.Shop
import com.ammyt.madridshops.R
import com.ammyt.madridshops.utils.INTENT_SHOP_DETAIL
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        testing_text.text =  shop.name
    }
}
