package com.ammyt.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

// Lo ideal sería crear 2 modelos. Uno con todos los campos parseados del JSON web y otro modelo
// con los datos que realmente se vayan a usar en el modelo
@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopEntity (
        val id: Long,
        val databaseId: Long,
        val name: String,
        @JsonProperty("description_en") val description: String,
        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String,
        val img: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en") val openingHours: String,
        val address: String = ""
)