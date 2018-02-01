package com.ammyt.madridshops.repository.cache

internal interface Cache {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}