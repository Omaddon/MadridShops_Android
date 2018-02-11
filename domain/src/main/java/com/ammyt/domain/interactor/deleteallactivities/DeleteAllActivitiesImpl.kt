package com.ammyt.domain.interactor.deleteallactivities

import android.content.Context
import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure
import com.ammyt.madridshops.repository.RepositoryImpl
import java.lang.ref.WeakReference

class DeleteAllActivitiesImpl(context: Context): DeleteAllActivities {
    val weakContext = WeakReference<Context>(context)

    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val repository = RepositoryImpl(weakContext.get()!!)
        repository.deleteAllActivities(success, error)
    }
}