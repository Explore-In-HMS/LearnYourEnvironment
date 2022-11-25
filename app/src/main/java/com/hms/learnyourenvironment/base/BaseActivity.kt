package com.hms.learnyourenvironment.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base class for activity instances
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var activityViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = getActivityViewBinding(layoutInflater)
        setContentView(activityViewBinding.root)

        setup()
    }

    open fun setup() {}

    abstract fun getActivityViewBinding(inflater: LayoutInflater): VB

}