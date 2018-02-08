package com.ammyt.madridshops.repository

import com.ammyt.madridshops.repository.model.ShopEntity

interface Repository {
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
    // TODO métodos de repositorio para Activities: get/delete AllActivities
}