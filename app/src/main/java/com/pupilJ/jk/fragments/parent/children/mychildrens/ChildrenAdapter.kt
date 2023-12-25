package com.pupilJ.jk.fragments.parent.children.mychildrens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.pupilJ.domain.models.Children
import com.pupilJ.jk.databinding.LayoutItemChildrenBinding

class ChildrenAdapter (private val onClickListener: OnClickListener) : ListAdapter<Children, ChildrenAdapter.ChildrenViewHolder>(ChildrenDiffUtil()) {

    class ChildrenViewHolder(private val itemChildrenBinding: LayoutItemChildrenBinding):RecyclerView.ViewHolder(itemChildrenBinding.root){
        fun bind(children: Children){
            itemChildrenBinding.children = children
            itemChildrenBinding.executePendingBindings()
        }
    }
    class ChildrenDiffUtil : DiffUtil.ItemCallback<Children>(){
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChildrenViewHolder(LayoutItemChildrenBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            onClickListener.onClick(getItem(position))
        }
    }
}