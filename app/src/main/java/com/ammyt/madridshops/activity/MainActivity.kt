package com.ammyt.madridshops.activity

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.internetstatus.InternetStatusInteractorImpl
import com.ammyt.madridshops.R
import com.ammyt.madridshops.router.Router
import com.ammyt.madridshops.utils.getActivitiesButtonText
import com.ammyt.madridshops.utils.getShopButtonText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInternetStatus()
    }

    private fun checkInternetStatus() {
        disableButtons()

        InternetStatusInteractorImpl().execute(this, success = {

            enableButtons()
            setupButtons()

        }, error = {
            AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(it)
                    .setPositiveButton("Retry?", { dialog, which ->
                        dialog.dismiss()
                        checkInternetStatus()
                    })
                    .setNegativeButton("Exit", { dialog, which ->
                        finish()
                    })
                    .show()
        })
    }

    private fun disableButtons() {
        shop_button.visibility = View.INVISIBLE
        activities_button.visibility = View.INVISIBLE
    }

    private fun enableButtons() {
        shop_button.visibility = View.VISIBLE
        activities_button.visibility = View.VISIBLE
    }

    private fun setupButtons() {
        shop_button.text = getShopButtonText()
        activities_button.text = getActivitiesButtonText()

        shop_button.setOnClickListener { Router().navigateFromMainActivityToShopActivity(this) }
        activities_button.setOnClickListener { Router().navigateFromMainActivityToActivitiesActivity(this) }
    }
}
