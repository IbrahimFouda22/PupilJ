package com.pupilJ.jk.fragments.parent.children.chat.teacher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pupilJ.domain.models.Teacher
import com.pupilJ.jk.databinding.LayoutItemTeacherBinding

class TeachersAdapter (private val teachersOnClickListener: TeachersOnClickListener) : ListAdapter<Teacher, TeachersAdapter.TeachersViewHolder>(
    TeachersDiffUtil()
) {

    class TeachersViewHolder(private val layoutItemTeacherBinding: LayoutItemTeacherBinding):RecyclerView.ViewHolder(layoutItemTeacherBinding.root){
        fun bind(teacher: Teacher){
            layoutItemTeacherBinding.teacher = teacher
            layoutItemTeacherBinding.executePendingBindings()
        }
    }
    class TeachersDiffUtil : DiffUtil.ItemCallback<Teacher>(){
        override fun areItemsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeachersViewHolder(LayoutItemTeacherBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: TeachersViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            teachersOnClickListener.onClick(getItem(position))
        }
    }
}