package com.hms.learnyourenvironment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Base class for Fragment instances
 */
abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {
    lateinit var fragmentViewBinding: VB

    companion object {
        const val BASE_TAG = "BaseFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentViewBinding = getFragmentViewBinding(inflater, container)
        return fragmentViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
    }

    abstract fun getFragmentViewModel(): VM

    abstract fun getFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    private fun setup() {
        setupUi()
        setupListeners()
        setupObservers()
    }


    open fun setupListeners() {}
    open fun setupObservers() {}
    open fun setupUi() {}
}