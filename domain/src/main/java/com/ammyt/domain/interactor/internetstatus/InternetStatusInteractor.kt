package com.ammyt.domain.interactor.internetstatus

import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure

interface InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}