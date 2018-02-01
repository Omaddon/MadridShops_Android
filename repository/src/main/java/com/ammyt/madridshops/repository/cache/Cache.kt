package com.ammyt.madridshops.repository.cache

interface Cache {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}