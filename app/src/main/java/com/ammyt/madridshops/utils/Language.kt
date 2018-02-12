package com.ammyt.madridshops.utils

import com.ammyt.domain.model.Activity
import com.ammyt.domain.model.Shop
import java.util.*

fun getOpeningHours(shop: Shop): String {
    var result = ""

    when (getLanguage()) {
        "es" -> result = shop.openingHours_es
        else -> result = shop.openingHours_en
    }

    return result
}

fun getOpeningHours(activity: Activity): String {
    var result = ""

    when (getLanguage()) {
        "es" -> result = activity.openingHours_es
        else -> result = activity.openingHours_en
    }

    return result
}

fun getDescription(shop: Shop): String {
    var result = ""

    when (getLanguage()) {
        "es" -> result = shop.description_es
        else -> result = shop.description_en
    }

    return result
}

fun getDescription(activity: Activity): String {
    var result = ""

    when (getLanguage()) {
        "es" -> result = activity.description_es
        else -> result = activity.description_en
    }

    return result
}

fun getShopButtonText(): String {
    var result = ""

    when (getLanguage()) {
        "es" -> result = "TIENDAS"
        else -> result = "SHOPS"
    }

    return result
}

fun getActivitiesButtonText(): String {
    var result = ""

    when (getLanguage()) {
        "es" -> result = "ACTIVIDADES"
        else -> result = "ACTIVITIES"
    }

    return result
}

private fun getLanguage(): String {
    return Locale.getDefault().language
}