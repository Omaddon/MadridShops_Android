package com.ammyt.madridshops.repository.network

import com.ammyt.madridshops.repository.ErrorCompletion
import com.ammyt.madridshops.repository.SuccessCompletion

interface GetJsonManager {
    fun execute(url: String,
                successCompletion: SuccessCompletion<String>,
                errorCompletion: ErrorCompletion)
}