package com.ammyt.domain.interactor.deleteallactivities

import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure

interface DeleteAllActivities {
    fun execute(success: CodeClosure, error: ErrorClosure)
}