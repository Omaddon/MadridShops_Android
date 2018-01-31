package com.ammyt.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ammyt.madridshops.repository.network.GetJsonManager
import com.ammyt.madridshops.repository.network.GetJsonManagerVolleyImpl
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class VolleyTests {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    @Throws(Exception::class)
    fun given_valid_url_we_get_non_null_json_as_string() {
        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(appContext)

        jsonManager.execute(url, successCompletion = object : SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                assertTrue(e.isNotEmpty())
            }

        }, errorCompletion = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                assertTrue(false)
            }

        })
    }
}
