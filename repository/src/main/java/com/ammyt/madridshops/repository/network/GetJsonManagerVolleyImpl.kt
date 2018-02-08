package com.ammyt.madridshops.repository.network

import android.content.Context
import com.ammyt.madridshops.repository.ErrorCompletion
import com.ammyt.madridshops.repository.SuccessCompletion
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.lang.ref.WeakReference

internal class GetJsonManagerVolleyImpl(context: Context): GetJsonManager {

    // WeakReference to avoid memory leak and cycle references
    // Activity -> Interactor -> Repository -> Volley -/> Activity
    var weakContext: WeakReference<Context> = WeakReference(context)
    var requestQueue: RequestQueue? = null

    override fun execute(url: String,
                         success: SuccessCompletion<String>,
                         error: ErrorCompletion) {
        // Get request queue: requestQueue()

        // Create request (success, failure)
        val request = StringRequest(url, Response.Listener {
            // it = JSON de la URL
            success.successCompletion(it)
        }, Response.ErrorListener {
            error.errorCompletion(it.localizedMessage)
        })

        // Add request to queue
        requestQueue().add(request)
    }

    private fun requestQueue(): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(weakContext.get())
        }

        return requestQueue!!
    }
}