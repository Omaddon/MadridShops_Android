package com.ammyt.domain.interactor.internetstatus

import android.content.Context
import android.net.ConnectivityManager
import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(context: Context, success: CodeClosure, error: ErrorClosure) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        if (connectivityManager != null) {
            val netInfo = connectivityManager.activeNetworkInfo

            if (netInfo != null && netInfo.isConnected) {
                success()
            } else {
                error("Conexion error. Unable connect to server.")
            }
        } else {
            error("Conexion error. Unable connect to server.")
        }
    }
}