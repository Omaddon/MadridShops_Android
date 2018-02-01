package com.ammyt.domain.interactor.deleteallshops

import android.content.Context
import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure
import com.ammyt.madridshops.repository.RepositoryImpl
import java.lang.ref.WeakReference

class DeleteAllShopsImpl(context: Context): DeleteAllShops {
    val weakContext = WeakReference<Context>(context)

    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val repository = RepositoryImpl(weakContext.get()!!)
        repository.deleteAllShops(success, error)
    }
}