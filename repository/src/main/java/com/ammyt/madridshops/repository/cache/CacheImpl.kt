package com.ammyt.madridshops.repository.cache

import android.content.Context
import com.ammyt.madridshops.repository.db.DBHelper
import com.ammyt.madridshops.repository.db.buildDBHelper
import com.ammyt.madridshops.repository.db.dao.ShopDAO
import com.ammyt.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

class CacheImpl(context: Context): Cache {
    private val weakContext = WeakReference<Context>(context)

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        // Como puede tardar un tiempo, lo hacemos en segundo plano
        Thread(Runnable {
            val successDeleting = ShopDAO(cacheDBHelper()).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("💩 Error deleting all shops from cache.")
                }
            })
        }).run()
    }

    private fun cacheDBHelper(): DBHelper {
        return buildDBHelper(weakContext.get()!!, "MadridShops.sqlite", 1)
    }
}