package com.ammyt.madridshops.router

import android.content.Intent
import com.ammyt.madridshops.activity.ShopActivity
import com.ammyt.madridshops.activity.PicassoActivity

class Router {

    fun navigateFromMainActivityToPicassoActivity(shop: ShopActivity) {
        shop.startActivity(Intent(shop, PicassoActivity::class.java))
    }
}