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


import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
// Handiling permission
object PermissionUtil {

    @JvmInline
    value class Permission(val result: ActivityResultLauncher<Array<String>>)

    sealed class PermissionState {
        object Granted : PermissionState()
        object Denied : PermissionState()
        object PermanentlyDenied : PermissionState()
    }

    private fun getPermissionState(
        activity: Activity?,
        result: MutableMap<String, Boolean>
    ): PermissionState {
        val deniedList: List<String> = result.filter {
            it.value.not()
        }.map {
            it.key
        }

        var state = when (deniedList.isEmpty()) {
            true -> PermissionState.Granted
            false -> PermissionState.Denied
        }

        if (state == PermissionState.Denied) {
            val permanentlyMappedList = deniedList.map {
                activity?.let { activity ->
                    shouldShowRequestPermissionRationale(activity, it)
                }
            }

            if (permanentlyMappedList.contains(false)) {
                state = PermissionState.PermanentlyDenied
            }
        }
        return state
    }

    fun Fragment.registerPermission(onPermissionResult: (PermissionState) -> Unit): Permission {
        return Permission(
            this.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                onPermissionResult(getPermissionState(activity, it.toMutableMap()))
            }
        )
    }

    fun AppCompatActivity.registerPermission(onPermissionResult: (PermissionState) -> Unit): Permission {
        return Permission(
            this.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                onPermissionResult(getPermissionState(this, it.toMutableMap()))
            }
        )
    }


}