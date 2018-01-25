package com.ammyt.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.GetAllShopsFakeImplementation
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.model.Shops

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code app wide
        Log.d("App", "onCreate")

        val allShopsInteractor = GetAllShopsFakeImplementation()
        allShopsInteractor.execute(success = object: SuccessCompletion<Shops> {
            override fun successCompletion(shops: Shops) {

            }

        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {

            }

        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}