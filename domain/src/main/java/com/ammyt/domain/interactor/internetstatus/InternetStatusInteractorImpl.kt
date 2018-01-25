package com.ammyt.domain.interactor.internetstatus

import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}