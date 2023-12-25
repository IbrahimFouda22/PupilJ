package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.pupilJ.domain.models.ActivityType
import com.pupilJ.jk.R

class AddActivityAdapter (private val list: List<ActivityType>, private val addActivityOnClickListener: AddActivityOnClickListener) : RecyclerView.Adapter<AddActivityAdapter.AddActivityViewHolder>() {

    class AddActivityViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val txt: TextView = itemView.findViewById(R.id.txt)
        val img: ImageView = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
        return AddActivityViewHolder(view.inflate(R.layout.layout_item_add_activity,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AddActivityViewHolder, position: Int) {
        holder.txt.text = list[position].name
        holder.img.setImageResource(list[position].image)
        holder.itemView.setOnClickListener{
            addActivityOnClickListener.onClick(list[position])
        }
    }

}