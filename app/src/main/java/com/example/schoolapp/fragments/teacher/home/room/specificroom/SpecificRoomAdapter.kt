package com.example.schoolapp.fragments.teacher.home.room.specificroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ChildrenTeacher
import com.example.domain.models.Classes
import com.example.schoolapp.databinding.LayoutItemChildrenBinding
import com.example.schoolapp.databinding.LayoutItemChildrenTeacherBinding
import com.example.schoolapp.databinding.LayoutItemRoomBinding

class SpecificRoomAdapter (private val specificRoomOnClickListener: SpecificRoomOnClickListener) : ListAdapter<ChildrenTeacher, SpecificRoomAdapter.SpecificRoomViewHolder>(SpecificRoomDiffUtil()) {

    class SpecificRoomViewHolder(private val itemChildrenTeacherBinding: LayoutItemChildrenTeacherBinding):RecyclerView.ViewHolder(itemChildrenTeacherBinding.root){
        fun bind(childrenTeacher: ChildrenTeacher){
            itemChildrenTeacherBinding.children = childrenTeacher
            itemChildrenTeacherBinding.executePendingBindings()
        }
    }
    class SpecificRoomDiffUtil : DiffUtil.ItemCallback<ChildrenTeacher>(){
        override fun areItemsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificRoomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SpecificRoomViewHolder(LayoutItemChildrenTeacherBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: SpecificRoomViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            specificRoomOnClickListener.onClick(getItem(position))
        }
    }
}