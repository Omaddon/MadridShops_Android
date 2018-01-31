package com.ammyt.madridshops.repository.model

data class ShopEntity (
        val id: Long,
        val databaseId: Long,
        val name: String,
        val description_en: String,
        val gps_lat: Float,
        val gps_lon: Float,
        val img: String,
        val logo_img: String,
        val opening_hours_es: String,
        val address: String
)