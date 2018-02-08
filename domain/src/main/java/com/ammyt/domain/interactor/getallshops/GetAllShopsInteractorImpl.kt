package com.ammyt.domain.interactor.getallshops

import android.content.Context
import android.util.Log
import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.model.Shop
import com.ammyt.domain.model.Shops
import com.ammyt.madridshops.repository.Repository
import com.ammyt.madridshops.repository.RepositoryImpl
import com.ammyt.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllShopsInteractorImpl(context: Context) : GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(success = {
            val shops: Shops = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error.errorCompletion(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {
        val shopsList = ArrayList<Shop>()

        list.forEach {
            shopsList.add(mapShopEntityToShop(it))
        }

        return Shops(shopsList)
    }

    private fun mapShopEntityToShop(shopEntity: ShopEntity): Shop {
        return Shop(
                shopEntity.id.toInt(),
                shopEntity.name,
                shopEntity.description_en,
                shopEntity.description_es,
                parseStringToDouble(shopEntity.latitude),
                parseStringToDouble(shopEntity.longitude),
                shopEntity.img,
                shopEntity.logo,
                shopEntity.openingHours_en,
                shopEntity.openingHours_es,
                shopEntity.address,
                shopEntity.telephone,
                shopEntity.url
        )
    }

    private fun parseStringToDouble(value: String): Double? {
        var coordinate: Double? = null

        val parsedString: String = value.replace(",", "").replace(" ", "")

        try {
            coordinate = parsedString.toDouble()
        } catch (e: Exception) {
            Log.d("PARSE ERROR", "💩 Error parsing to float: " + value)
        }

        return coordinate
    }
}
