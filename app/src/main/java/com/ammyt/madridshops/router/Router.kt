package com.ammyt.madridshops.router

import android.content.Intent
import com.ammyt.domain.model.Activity
import com.ammyt.domain.model.Shop
import com.ammyt.madridshops.activity.ActivitiesActivity
import com.ammyt.madridshops.activity.MainActivity
import com.ammyt.madridshops.activity.ShopActivity
import com.ammyt.madridshops.activity.ShopDetailActivity
import com.ammyt.madridshops.utils.INTENT_SHOP_DETAIL

class Router {

    fun navigateFromMainActivityToShopActivity(currentActivity: MainActivity) {
        val intent = Intent(currentActivity, ShopActivity::class.java)

        currentActivity.startActivity(intent)
    }

    fun navigateFromMainActivityToActivitiesActivity(currentActivity: MainActivity) {
        val intent = Intent(currentActivity, ActivitiesActivity::class.java)

        currentActivity.startActivity(intent)
    }

    fun navigateFromShopActivityToShopDetailActivity(currentActivity: ShopActivity, shop: Shop) {
        val intent = Intent(currentActivity, ShopDetailActivity::class.java)
        intent.putExtra(INTENT_SHOP_DETAIL, shop)

        currentActivity.startActivity(intent)
    }

    fun navigateFromActivitiesActivityToActivityDetailActivity(currentActivity: ActivitiesActivity, activity: Activity) {
        /*
        val intent = Intent(currentActivity, ActivityDetailActivity::class.java)
        intent.putExtra(INTENT_SHOP_DETAIL, activity)

        currentActivity.startActivity(intent)
        */
    }
}