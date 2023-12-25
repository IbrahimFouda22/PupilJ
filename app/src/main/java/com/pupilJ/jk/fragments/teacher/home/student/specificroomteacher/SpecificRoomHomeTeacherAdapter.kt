package com.pupilJ.jk.fragments.teacher.home.student.specificroomteacher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.jk.databinding.LayoutItemChildrenTeacherBinding

class SpecificRoomHomeTeacherAdapter (private val specificRoomHomeTeacherOnClickListener: SpecificRoomHomeTeacherOnClickListener) : ListAdapter<ChildrenTeacher, SpecificRoomHomeTeacherAdapter.SpecificRoomHomeTeacherViewHolder>(SpecificRoomHomeTeacherDiffUtil()) {

    class SpecificRoomHomeTeacherViewHolder(private val itemChildrenTeacherBinding: LayoutItemChildrenTeacherBinding):RecyclerView.ViewHolder(itemChildrenTeacherBinding.root){
        fun bind(childrenTeacher: ChildrenTeacher){
            itemChildrenTeacherBinding.children = childrenTeacher
            itemChildrenTeacherBinding.executePendingBindings()
        }
    }
    class SpecificRoomHomeTeacherDiffUtil : DiffUtil.ItemCallback<ChildrenTeacher>(){
        override fun areItemsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificRoomHomeTeacherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SpecificRoomHomeTeacherViewHolder(LayoutItemChildrenTeacherBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: SpecificRoomHomeTeacherViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            specificRoomHomeTeacherOnClickListener.onClick(getItem(position))
        }
    }
}