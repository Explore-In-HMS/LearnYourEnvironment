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
import dagger.hilt.android.AndroidEntryPoint

//we show animal list
@AndroidEntryPoint
class AnimalList : BaseFragment<AnimalListViewModel, FragmentAnimalListBinding>() {
    lateinit var animalLists: ArrayList<AnimalItem>
    private val viewModel: AnimalListViewModel by viewModels()
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
                " Always falls on its feets and has 9 lives.", R.raw.cat
            )
        )
        animalLists.add(
            AnimalItem(
                "dog",
                R.drawable.dog,
                "Loyalest friends to their owner.", R.raw.dog
            )
        )
        animalLists.add(
            AnimalItem(
                "bird",
                R.drawable.bird,
                "The sky is their home.", R.raw.bird
            )
        )
        animalLists.add(
            AnimalItem(
                "duck",
                R.drawable.duck,
                "Paddles away with its babies.", R.raw.duck
            )
        )
        animalLists.add(
            AnimalItem(
                "lion",
                R.drawable.lion,
                "He is the king of the jungle.", R.raw.lion
            )
        )
        animalLists.add(
            AnimalItem(
                "giraffle",
                R.drawable.giraffe,
                "Longest neck you will ever see.", R.raw.giraffe
            )
        )
        animalLists.add(
            AnimalItem(
                "monkey",
                R.drawable.monkey,
                "  Loves to eat banana while swinging through the jungle.", R.raw.monkey
            )
        )
        animalLists.add(
            AnimalItem(
                "elephant",
                R.drawable.elephant,
                " Might be the biggest but fears a mouse.", R.raw.elephant
            )
        )
        animalLists.add(
            AnimalItem(
                "chicken",
                R.drawable.chicken,
                "  Loves to eat banana while swinging through the jungle.", R.raw.chicken
            )
        )
        animalLists.add(
            AnimalItem(
                "dolphin",
                R.drawable.dolpin,
                "  Loves to eat banana while swinging through the jungle.", R.raw.dolphin
            )
        )
        animalLists.add(
            AnimalItem(
                "turtle",
                R.drawable.turtle,
                "  Loves to eat banana while swinging through the jungle.", R.raw.turtle
            )
        )
        animalLists.add(
            AnimalItem(
                "rhino",
                R.drawable.rhino,
                "  Loves to eat banana while swinging through the jungle.", R.raw.rhino
            )
        )
        animalLists.add(
            AnimalItem(
                "zebra",
                R.drawable.zebra,
                "  Loves to eat banana while swinging through the jungle.", R.raw.zebra
            )
        )
        animalLists.add(
            AnimalItem(
                "penguin",
                R.drawable.penguin,
                "  Loves to eat banana while swinging through the jungle.", R.raw.penguin
            )
        )
        animalLists.add(
            AnimalItem(
                "tiger",
                R.drawable.tiger,
                "  Loves to eat banana while swinging through the jungle.", R.raw.tiger
            )
        )
        animalLists.add(
            AnimalItem(
                "horse",
                R.drawable.horse,
                "  Loves to eat banana while swinging through the jungle.", R.raw.horse
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