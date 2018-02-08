package com.ammyt.domain.interactor.internetstatus

import com.ammyt.domain.BuildConfig
import com.ammyt.domain.interactor.CodeClosure
import com.ammyt.domain.interactor.ErrorClosure
import java.net.InetAddress

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val shopAddress: InetAddress = InetAddress.getByName(BuildConfig.MADRIDSHOPS_SERVER_URL)
        val activitiesAddress: InetAddress = InetAddress.getByName(BuildConfig.MADRIDACTIVITIES_SERVER_URL)

        if (shopAddress.isReachable(3000) && activitiesAddress.isReachable(3000)) {
            success()
        } else {
            error("Conexion Error. Unable connect to server.")
        }
    }
}