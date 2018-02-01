package com.ammyt.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.ammyt.domain.interactor.deleteallshops.DeleteAllShopsImpl
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

        DeleteAllShopsImpl(this).execute(success = {
            Log.d("Success", "❗️Success")
        }, error = {
            Log.d("Error", "❗️Error: " + it)
        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}