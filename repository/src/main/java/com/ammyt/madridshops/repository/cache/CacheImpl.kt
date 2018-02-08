package com.ammyt.madridshops.repository.cache

import android.content.Context
import com.ammyt.madridshops.repository.db.DBHelper
import com.ammyt.madridshops.repository.db.buildDBHelper
import com.ammyt.madridshops.repository.db.dao.ShopDAO
import com.ammyt.madridshops.repository.model.ShopEntity
import com.ammyt.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

internal class CacheImpl(context: Context): Cache {
    private val weakContext = WeakReference<Context>(context)

    override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach { ShopDAO(cacheDBHelper()).insert(it) }

                DispatchOnMainThread(Runnable {
                    success()
                })
            } catch (e: Exception) {
                DispatchOnMainThread(Runnable {
                    error("💩 Error saving all shops into db.")
                })
            }
        }).run()
    }

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            val shops = ShopDAO(cacheDBHelper()).query()

            DispatchOnMainThread(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("💩 Error getting all shops from cache. No shops")
                }
            })
        }).run()
    }

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
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
        // TODO quizá cambiar "version" por una constante en el build.gradle o una variable global
        return buildDBHelper(weakContext.get()!!, "MadridShops.sqlite", 1)
    }
}