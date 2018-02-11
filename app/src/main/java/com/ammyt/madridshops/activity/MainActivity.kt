package com.ammyt.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ammyt.madridshops.R
import com.ammyt.madridshops.router.Router
import com.ammyt.madridshops.utils.getActivitiesButtonText
import com.ammyt.madridshops.utils.getShopButtonText
import kotlinx.android.synthetic.main.activity_main.*

// TODO Mostrar mensaje de error si no hay internet
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shop_button.text = getShopButtonText()
        activities_button.text = getActivitiesButtonText()

        shop_button.setOnClickListener { Router().navigateFromMainActivityToShopActivity(this) }
        activities_button.setOnClickListener { Router().navigateFromMainActivityToActivitiesActivity(this) }
    }
}
