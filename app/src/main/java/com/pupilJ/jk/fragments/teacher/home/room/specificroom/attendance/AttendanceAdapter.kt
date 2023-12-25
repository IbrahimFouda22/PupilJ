package com.pupilJ.jk.fragments.teacher.home.room.specificroom.attendance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.jk.databinding.LayoutItemAttendanceBinding

class AttendanceAdapter (private val onClickListener: AttendanceOnClickListener) : ListAdapter<ChildrenTeacher, AttendanceAdapter.AttendanceViewHolder>(AttendanceDiffUtil()) {

    class AttendanceViewHolder(private val itemAttendanceBinding: LayoutItemAttendanceBinding):RecyclerView.ViewHolder(itemAttendanceBinding.root){
        fun bind(childrenTeacher: ChildrenTeacher){
            itemAttendanceBinding.children = childrenTeacher
            itemAttendanceBinding.executePendingBindings()
        }
        fun selected(toggle: Boolean,childrenTeacher: ChildrenTeacher) {
            childrenTeacher.isSelected = !toggle
            bind(childrenTeacher)
        }
    }
    class AttendanceDiffUtil : DiffUtil.ItemCallback<ChildrenTeacher>(){
        override fun areItemsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChildrenTeacher, newItem: ChildrenTeacher): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AttendanceViewHolder(LayoutItemAttendanceBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            holder.selected(getItem(position).isSelected,getItem(position))
            onClickListener.onClick(getItem(position))
        }
    }
}