package com.ammyt.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.ammyt.domain.model.Shops

// TODO descargar Activities, crear pantalla inicial, comprobar internetStatus
class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code app wide
        Log.d("App", "onCreate")

        Log.d("App", BuildConfig.MADRIDSHOPS_SERVER_URL)

        /*
        val allShopsInteractor = GetAllShopsInteractorImpl(this)

        allShopsInteractor.execute(object: SuccessCompletion<Shops> {
            override fun successCompletion(e: Shops) {
                Log.d("Shops", "❗️Count: " + e.count())

                //e.shops.forEach { Log.d("Shop", it.name) }
            }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("Shops", errorMessage)
            }

        })
        */

        /*
        DeleteAllShopsImpl(this).execute(success = {
            Log.d("Success", "❗️Success")
        }, error = {
            Log.d("Error", "❗️Error: " + it)
        })
        */
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}