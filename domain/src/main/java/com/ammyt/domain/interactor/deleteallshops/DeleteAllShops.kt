package com.ammyt.domain.interactor.deleteallshops

import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure

// TODO DeleteAll Activities
interface DeleteAllShops {
    fun execute(success: CodeClosure, error: ErrorClosure)
}