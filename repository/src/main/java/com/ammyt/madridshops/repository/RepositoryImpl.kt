package com.ammyt.madridshops.repository

import ammyt.com.repository.BuildConfig
import android.content.Context
import android.util.Log
import com.ammyt.madridshops.repository.cache.Cache
import com.ammyt.madridshops.repository.cache.CacheImpl
import com.ammyt.madridshops.repository.model.ShopEntity
import com.ammyt.madridshops.repository.model.ShopsResponseEntity
import com.ammyt.madridshops.repository.network.GetJsonManager
import com.ammyt.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.ammyt.madridshops.repository.network.json.JsonEntitiesParser
import com.ammyt.madridshops.repository.utils.ErrorCompletion
import com.ammyt.madridshops.repository.utils.SuccessCompletion
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import java.lang.ref.WeakReference

class RepositoryImpl(context: Context): Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get()!!)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit,
                             error: (errorMessage: String) -> Unit) {

        // Read all shops from cache
        cache.getAllShops(success = {
            // If there's shops in cache -> return them
            success(it)
        }, error = {
            // If not shops in cache -> network + Cache
            populateShopCache(success, error)
        })
    }

    // TODO populate cache for Activities
    private fun populateShopCache(success: (shops: List<ShopEntity>) -> Unit,
                                  error: (errorMessage: String) -> Unit) {

        // Perform network request
        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MADRIDSHOPS_SERVER_URL,
                successCompletion = object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        // e = our JSON
                        // parsing: be care with parse errors!!
                        val parser = JsonEntitiesParser()
                        val responseEntity: ShopsResponseEntity

                        try {
                            responseEntity = parser.parse<ShopsResponseEntity>(e)
                        } catch (e: InvalidFormatException) {
                            error("💩 Error parsing.")
                            return
                        }

                        // Store result in cache
                        cache.saveAllShops(responseEntity.result, success = {
                            // Get results just stored (sorted)
                            cache.getAllShops(success = {
                                success(it)
                            }, error = { })
                        }, error = {
                            error(it)
                        })
                    }

                }, errorCompletion = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("Volley", "💩 Error downloading JSON. Volley fails!")
            }
        })
    }

    override fun deleteAllShops(success: () -> Unit,
                                error: (errorMessage: String) -> Unit) {
        cache.deleteAllShops(success, error)
    }
}