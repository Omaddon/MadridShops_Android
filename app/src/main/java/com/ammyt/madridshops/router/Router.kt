package com.ammyt.madridshops.router

import android.content.Intent
import com.ammyt.madridshops.activity.MainActivity
import com.ammyt.madridshops.activity.PicassoActivity

class Router {

    fun navigateFromMainActivityToPicassoActivity(main: MainActivity) {
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }
}