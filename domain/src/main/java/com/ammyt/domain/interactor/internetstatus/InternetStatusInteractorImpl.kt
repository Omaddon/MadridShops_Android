package com.ammyt.domain.interactor.internetstatus

import android.content.Context
import android.net.ConnectivityManager
import com.ammyt.domain.BuildConfig
import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure
import com.ammyt.madridshops.repository.thread.DispatchOnMainThread
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

// TODO implementar internetStatus
class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(context: Context, success: CodeClosure, error: ErrorClosure) {
        success()
    }
}