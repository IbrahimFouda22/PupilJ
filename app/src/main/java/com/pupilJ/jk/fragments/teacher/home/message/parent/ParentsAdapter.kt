package com.pupilJ.jk.fragments.teacher.home.message.parent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.pupilJ.domain.models.Classes
import com.pupilJ.domain.models.Parent
import com.pupilJ.jk.databinding.LayoutItemParentBinding
import com.pupilJ.jk.databinding.LayoutItemRoomBinding

class ParentsAdapter (private val parentsOnClickListener: ParentsOnClickListener) : ListAdapter<Parent, ParentsAdapter.ParentsViewHolder>(
    ParentsDiffUtil()
) {

    class ParentsViewHolder(private val layoutItemParentBinding: LayoutItemParentBinding):RecyclerView.ViewHolder(layoutItemParentBinding.root){
        fun bind(parent: Parent){
            layoutItemParentBinding.parent = parent
            layoutItemParentBinding.executePendingBindings()
        }
    }
    class ParentsDiffUtil : DiffUtil.ItemCallback<Parent>(){
        override fun areItemsTheSame(oldItem: Parent, newItem: Parent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Parent, newItem: Parent): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ParentsViewHolder(LayoutItemParentBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ParentsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            parentsOnClickListener.onClick(getItem(position))
        }
    }
}