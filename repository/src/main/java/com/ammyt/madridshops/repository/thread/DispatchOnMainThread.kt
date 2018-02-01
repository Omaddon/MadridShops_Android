package com.ammyt.madridshops.repository.thread

import android.os.Handler
import android.os.Looper


fun DispatchOnMainThread(codeToRun: Runnable) {
    // El Looper es la cola de ejecucción
    val uiHandler: Handler = Handler(Looper.getMainLooper())
    uiHandler.post(codeToRun)
}