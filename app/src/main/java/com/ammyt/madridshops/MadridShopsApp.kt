package com.ammyt.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.ammyt.domain.interactor.getallshops.GetAllShopsFakeImpl
import com.ammyt.domain.model.Shops

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code app wide
        Log.d("App", "onCreate")

        val allShopsInteractor = GetAllShopsFakeImpl()

        allShopsInteractor.execute(success = { shops: Shops ->

        }, error = {msg: String ->

        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}