package com.ammyt.domain.interactor

import com.ammyt.domain.model.Shop
import com.ammyt.domain.model.Shops

class GetAllShopsFakeImplementation: GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shops = createFakeListOfShops()

            success.successCompletion(shops)
        } else {
            error.errorCompletion("💩 error")
        }
    }

    fun execute(success: (shops: Shops) -> Unit, error: (msg: String) -> Unit ) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shops = createFakeListOfShops()

            success(shops)
        } else {
            error("💩 error")
        }
    }

    fun createFakeListOfShops(): Shops {
        val list = ArrayList<Shop>()

        for (i in 0..100) {
            val shop = Shop(i, "Shop " + i, "Address " + i)
            list.add(shop)
        }

        return Shops(list)
    }

}