package com.pupilJ.jk.fragments.teacher.home.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pupilJ.domain.models.Reminder
import com.pupilJ.jk.databinding.LayoutItemReminderBinding


class ReminderAdapter (private val reminderOnClickListener: ReminderOnClickListener) : ListAdapter<Reminder, ReminderAdapter.ReminderViewHolder>(
    ReminderDiffUtil()
) {

    class ReminderViewHolder(private val itemReminderBinding: LayoutItemReminderBinding):RecyclerView.ViewHolder(itemReminderBinding.root){
        val iconDel = itemReminderBinding.imgBtnDeleteReminder
        fun bind(reminder: Reminder){
            itemReminderBinding.item = reminder
            itemReminderBinding.executePendingBindings()
        }



    }
    class ReminderDiffUtil : DiffUtil.ItemCallback<Reminder>(){
        override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem.id != newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReminderViewHolder(LayoutItemReminderBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.iconDel.setOnClickListener{
            reminderOnClickListener.onClick(getItem(position).id,position)
        }

    }
}