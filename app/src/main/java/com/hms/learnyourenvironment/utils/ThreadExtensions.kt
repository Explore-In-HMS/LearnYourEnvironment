/*
 *
 *  * Copyright 2021. Huawei Technologies Co., Ltd. All rights reserved.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *  *
 *
 */
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