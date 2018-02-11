package com.ammyt.domain.interactor.getallactivities

import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.model.Activities
import com.ammyt.domain.model.Shops

interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion)
}