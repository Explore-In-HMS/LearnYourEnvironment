package com.hms.learnyourenvironment.ui.animal_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hms.learnyourenvironment.R
import com.hms.learnyourenvironment.base.BaseFragment
import com.hms.learnyourenvironment.data.model.AnimalItem
import com.hms.learnyourenvironment.databinding.FragmentAnimalListBinding


class AnimalList : BaseFragment<AnimalListViewModel, FragmentAnimalListBinding>() {
    lateinit var animalLists: ArrayList<AnimalItem>
    private val viewModel: AnimalListViewModel by viewModels()
    private lateinit var adapter: AnimalListAdapter
    override fun getFragmentViewModel(): AnimalListViewModel {
        return viewModel
    }

    override fun getFragmentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnimalListBinding {
        return FragmentAnimalListBinding.inflate(inflater, container, false)
    }

    override fun setupUi() {
        super.setupUi()
        animalLists = ArrayList()
        animalLists.add(
            AnimalItem(
                "cat",
                R.drawable.cat,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        animalLists.add(
            AnimalItem(
                "dog",
                R.drawable.dog,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        animalLists.add(
            AnimalItem(
                "bird",
                R.drawable.bird,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        animalLists.add(
            AnimalItem(
                "duck",
                R.drawable.duck,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        animalLists.add(
            AnimalItem(
                "lion",
                R.drawable.lion,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        animalLists.add(
            AnimalItem(
                "giraffle",
                R.drawable.giraffe,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        animalLists.add(
            AnimalItem(
                "monkey",
                R.drawable.monkey,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        animalLists.add(
            AnimalItem(
                "elephant",
                R.drawable.elephant,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        val layout = GridLayoutManager(requireContext(), 2)
        fragmentViewBinding.animalListRV.layoutManager = layout
        fragmentViewBinding.animalListRV.adapter = AnimalListAdapter(animalLists, requireContext())

        fragmentViewBinding.animalListRV.adapter?.notifyDataSetChanged()
    }

    override fun setupListeners() {
        super.setupListeners()
        var adapter: AnimalListAdapter =
            fragmentViewBinding.animalListRV.adapter as AnimalListAdapter
        adapter.setOnClickListener(object : AnimalListAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val action =
                    AnimalListDirections.actionAnimalListToAnimalDetail(animalLists.get(position))
                view?.findNavController()?.navigate(action)
            }

        })
    }


}