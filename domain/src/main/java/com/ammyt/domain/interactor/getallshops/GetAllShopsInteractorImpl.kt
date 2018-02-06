package com.ammyt.domain.interactor.getallshops

import android.content.Context
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
            error(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {
        val shopsList = ArrayList<Shop>()

        // TODO mapear el resto de campos (meter en función)
        list.forEach {
            val shop = Shop(it.id.toInt(), it.name, it.address)
            shopsList.add(shop)
        }

        return Shops(shopsList)
    }
}
