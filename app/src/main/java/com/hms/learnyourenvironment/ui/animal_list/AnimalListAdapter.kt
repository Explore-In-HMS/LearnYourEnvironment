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

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hms.learnyourenvironment.R
import com.hms.learnyourenvironment.data.model.AnimalItem

// This class is Adapter for Animal List Fragment
class AnimalListAdapter(
    private val animalList: ArrayList<AnimalItem>,
    private val context: Context,
) : RecyclerView.Adapter<AnimalListAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimalListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.animal_item,
            parent, false
        )

        return AnimalListAdapter.ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: AnimalListAdapter.ViewHolder, position: Int) {
        holder.animalName.text = animalList.get(position).animalName
        holder.animalId.setImageResource(animalList.get(position).animalImg)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    class ViewHolder(ItemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(ItemView) {
        val animalName: TextView = itemView.findViewById(R.id.animalName)
        val animalId: ImageView = itemView.findViewById(R.id.animalId)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
