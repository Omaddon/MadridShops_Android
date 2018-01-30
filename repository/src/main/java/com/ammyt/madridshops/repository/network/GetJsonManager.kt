package com.ammyt.madridshops.repository.network

import java.net.URL

interface GetJsonManager {
    fun execute(url: String)
}