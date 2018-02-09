package com.ammyt.madridshops.router

import android.content.Intent
import com.ammyt.madridshops.activity.ShopActivity
import com.ammyt.madridshops.activity.PicassoActivity
import com.ammyt.madridshops.activity.ShopDetailActivity

class Router {

    fun navigateFromShopActivityToShopDetailActivity(currentActivity: ShopActivity) {
        currentActivity.startActivity(Intent(currentActivity, ShopDetailActivity::class.java))
    }
}