package com.hms.learnyourenvironment.utils

import android.os.Handler
import android.os.Looper

typealias VoidFunc = (() -> Unit)

fun fireNForget(runnable: Runnable?) {
    Thread(runnable).start()
}

fun fireNForget(runnable: VoidFunc) {
    Thread(runnable).start()
}

fun fireNForget(runnable: Runnable?, canShout: Boolean) {
    Thread(Runnable {
        if (canShout) {
            runnable?.run()
        } else {
            try {
                runnable?.run()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }
    }).start()
}

fun doOnUI(func: VoidFunc) {
    Handler(Looper.getMainLooper()).post {
        func.invoke()
    }
}

fun doAfter(milliSeconds: Long, onMainThread: Boolean = true, func: VoidFunc) {
    if (onMainThread) {
        Handler(Looper.getMainLooper()).postDelayed({
            func.invoke()
        }, milliSeconds)
    } else {
        fireNForget {
            try {
                Thread.sleep(milliSeconds)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            func.invoke()
        }
    }
}