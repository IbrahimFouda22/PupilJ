package com.pupilJ.jk.fragments.teacher.home.reminder.children

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.jk.databinding.LayoutItemChildrenTeacherBinding

class ChildrenReminderAdapter (private val childrenReminderOnClickListener: ChildrenReminderOnClickListener) : ListAdapter<ChildrenTeacher, ChildrenReminderAdapter.ChildrenReminderViewHolder>(ChildrenReminderDiffUtil()) {

    class ChildrenReminderViewHolder(private val itemChildrenTeacherBinding: LayoutItemChildrenTeacherBinding):RecyclerView.ViewHolder(itemChildrenTeacherBinding.root){
        fun bind(childrenTeacher: ChildrenTeacher){
            itemChildrenTeacherBinding.children = childrenTeacher
            itemChildrenTeacherBinding.executePendingBindings()
        }
    }
    class ChildrenReminderDiffUtil : DiffUtil.ItemCallback<ChildrenTeacher>(){
        override fun areItemsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenReminderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChildrenReminderViewHolder(LayoutItemChildrenTeacherBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ChildrenReminderViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            childrenReminderOnClickListener.onClick(getItem(position))
        }
    }
}