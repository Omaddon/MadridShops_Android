package com.ammyt.madridshops.repository.network

import com.ammyt.madridshops.repository.utils.ErrorCompletion
import com.ammyt.madridshops.repository.utils.SuccessCompletion

internal interface GetJsonManager {
    fun execute(url: String,
                successCompletion: SuccessCompletion<String>,
                errorCompletion: ErrorCompletion)
}