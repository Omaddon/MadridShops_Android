package com.ammyt.domain.interactor.internetstatus

import android.content.Context
import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure

interface InternetStatusInteractor {
    fun execute(context: Context, success: CodeClosure, error: ErrorClosure)
}