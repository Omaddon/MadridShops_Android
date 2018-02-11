package com.ammyt.madridshops.repository

import com.ammyt.madridshops.repository.model.ActivityEntity
import com.ammyt.madridshops.repository.model.ShopEntity

interface Repository {
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)

    fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllActivities(success: () -> Unit, error: (errorMessage: String) -> Unit)
}