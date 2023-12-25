package com.pupilJ.jk.fragments.teacher.home.reminder.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.pupilJ.domain.models.Classes
import com.pupilJ.jk.databinding.LayoutItemRoomBinding

class RoomsReminderAdapter (private val roomsReminderOnClickListener: RoomsReminderOnClickListener) : ListAdapter<Classes, RoomsReminderAdapter.RoomsReminderViewHolder>(RoomsReminderDiffUtil()) {

    class RoomsReminderViewHolder(private val itemRoomBinding: LayoutItemRoomBinding):RecyclerView.ViewHolder(itemRoomBinding.root){
        fun bind(classes: Classes){
            itemRoomBinding.room = classes
            itemRoomBinding.executePendingBindings()
        }
    }
    class RoomsReminderDiffUtil : DiffUtil.ItemCallback<Classes>(){
        override fun areItemsTheSame(oldItem: Classes, newItem: Classes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Classes, newItem: Classes): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsReminderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RoomsReminderViewHolder(LayoutItemRoomBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: RoomsReminderViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            roomsReminderOnClickListener.onClick(getItem(position))
        }
    }
}