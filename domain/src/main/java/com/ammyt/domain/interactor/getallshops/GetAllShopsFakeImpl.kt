package com.ammyt.domain.interactor.getallshops

import com.ammyt.domain.interactor.ErrorClosure
import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.SuccessClosure
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.model.Shop
import com.ammyt.domain.model.Shops

class GetAllShopsFakeImpl : GetAllShopsInteractor {
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

    fun execute(success: SuccessClosure, error: ErrorClosure) {
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
            val shop = Shop(
                    i,
                    "Shop " + i,
                    "Address " + i,
                    "Address " + i,
                    41.0,
                    -3.0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "")

            list.add(shop)
        }

        return Shops(list)
    }

}