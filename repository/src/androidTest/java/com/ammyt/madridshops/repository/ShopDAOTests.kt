package com.ammyt.madridshops.repository

import com.ammyt.madridshops.repository.db.buildDBHelper
import com.ammyt.madridshops.repository.db.dao.ShopDAO
import com.ammyt.madridshops.repository.model.ShopEntity
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class ShopDAOTests {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbHelper = buildDBHelper(appContext, "mydb.sqlite", 1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {

        val shopEntityDao = ShopDAO(dbHelper)
        shopEntityDao.deleteAll()

        val shop1 = ShopEntity(
                1,
                1,
                "My Shop 1",
                "desc 1",
                1.0f,
                2.0f,
                "",
                "",
                "",
                "")

        val shop2 = ShopEntity(
                2,
                1,
                "My Shop 2",
                "desc 2",
                1.0f,
                2.0f,
                "",
                "",
                "",
                "")

        val id1 = shopEntityDao.insert(shop1)
        val id2 = shopEntityDao.insert(shop2)

        shopEntityDao.query().forEach {
            Log.d("ShopTest", it.name)
        }

        assertTrue(id1 > 0)
        assertTrue(id2 > 0)
    }
}
