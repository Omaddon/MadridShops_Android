package com.ammyt.madridshops.router

import android.content.Intent
import com.ammyt.domain.model.Shop
import com.ammyt.madridshops.activity.ShopActivity
import com.ammyt.madridshops.activity.ShopDetailActivity
import com.ammyt.madridshops.utils.INTENT_SHOP_DETAIL

class Router {

    fun navigateFromShopActivityToShopDetailActivity(currentActivity: ShopActivity, shop: Shop) {
        val intent = Intent(currentActivity, ShopDetailActivity::class.java)
        intent.putExtra(INTENT_SHOP_DETAIL, shop)

        currentActivity.startActivity(intent)
    }
}