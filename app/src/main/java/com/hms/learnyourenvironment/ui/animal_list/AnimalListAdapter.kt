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
