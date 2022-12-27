package com.hms.learnyourenvironment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.hms.learnyourenvironment.R
import com.hms.learnyourenvironment.base.BaseFragment
import com.hms.learnyourenvironment.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun getFragmentViewModel(): HomeViewModel {
        return viewModel
    }

    override fun getFragmentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupUi() {
        super.setupUi()

        fragmentViewBinding.imageButton.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToAnimalList()
            view?.findNavController()?.navigate(action)
        }
    }


}