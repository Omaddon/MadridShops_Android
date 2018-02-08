package com.ammyt.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

// TODO crear modelo para Activities y responseEntity
@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopEntity (
        val id: Long,
        val databaseId: Long,
        val name: String,
        @JsonProperty("description_en") val description_en: String,
        @JsonProperty("description_es") val description_es: String,
        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String,
        val img: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en") val openingHours_en: String,
        @JsonProperty("opening_hours_es") val openingHours_es: String,
        val address: String,
        val telephone: String,
        val url: String
)