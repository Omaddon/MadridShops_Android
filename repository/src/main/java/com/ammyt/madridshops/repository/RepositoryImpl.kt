package com.ammyt.madridshops.repository

import ammyt.com.repository.BuildConfig
import android.content.Context
import com.ammyt.madridshops.repository.cache.Cache
import com.ammyt.madridshops.repository.cache.CacheImpl
import com.ammyt.madridshops.repository.model.ShopEntity
import com.ammyt.madridshops.repository.model.ShopsResponseEntity
import com.ammyt.madridshops.repository.network.GetJsonManager
import com.ammyt.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.ammyt.madridshops.repository.network.json.JsonEntitiesParser
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
            populateCache(success, error)
        })
    }

    private fun populateCache(success: (shops: List<ShopEntity>) -> Unit,
                              error: (errorMessage: String) -> Unit) {

        // Perform network request
        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MADRIDSHOPS_SERVER_URL,
                successCompletion = object: SuccessCompletion<String>{
                    override fun successCompletion(e: String) {
                        // parsing: be care with parse errors!!
                        val parser = JsonEntitiesParser()
                        var responseEntity: ShopsResponseEntity

                        try {
                            responseEntity = parser.parse<ShopsResponseEntity>(e)
                        } catch (e: InvalidFormatException) {
                            error("💩 Error parsing.")
                            return
                        }

                        // Store result in cache
                        cache.saveAllShops(responseEntity.result, success = {
                            success(responseEntity.result)
                        }, error = {
                            error(it)
                        })
                    }

                }, errorCompletion = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {

            }

        })
    }

    override fun deleteAllShops(success: () -> Unit,
                                error: (errorMessage: String) -> Unit) {

        cache.deleteAllShops(success, error)
    }
}