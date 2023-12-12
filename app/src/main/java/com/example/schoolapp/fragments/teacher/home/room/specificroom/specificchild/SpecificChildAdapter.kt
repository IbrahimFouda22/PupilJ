package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ChildrenActivity
import com.example.domain.models.toCustomize
import com.example.domain.models.toCustomizeFood
import com.example.domain.models.toCustomizeHealthCheck
import com.example.domain.models.toCustomizeIncidents
import com.example.domain.models.toCustomizeNameToFace
import com.example.domain.models.toCustomizeNap
import com.example.domain.models.toCustomizeNoteAchievementMeds
import com.example.domain.models.toCustomizePhoto
import com.example.domain.models.toCustomizePotty
import com.example.domain.models.toCustomizeVideo
import com.example.schoolapp.R
import com.example.schoolapp.databinding.LayoutItemActivityBinding

class SpecificChildAdapter(private val studentName:String) : ListAdapter<ChildrenActivity, SpecificChildAdapter.SpecificRoomViewHolder>(SpecificChildDiffUtil()) {

    class SpecificRoomViewHolder(private val itemActivityBinding: LayoutItemActivityBinding):RecyclerView.ViewHolder(itemActivityBinding.root){
        fun bind(childrenActivity: ChildrenActivity , studentName: String){
            if(childrenActivity.activityType == "food")
                itemActivityBinding.item = childrenActivity.toCustomizeFood(R.drawable.img_food)
            if(childrenActivity.activityType == "nap")
                itemActivityBinding.item = childrenActivity.toCustomizeNap(R.drawable.img_nap, "$studentName ${childrenActivity.napType}")
            if(childrenActivity.activityType == "note")
                itemActivityBinding.item = childrenActivity.toCustomizeNoteAchievementMeds(R.drawable.img_note, childrenActivity.txtDescription)
            if(childrenActivity.activityType == "observation")
                itemActivityBinding.item = childrenActivity.toCustomize(R.drawable.img_observation)
            if(childrenActivity.activityType == "meds")
                itemActivityBinding.item = childrenActivity.toCustomizeNoteAchievementMeds(R.drawable.img_med,childrenActivity.txtDescription)
            if(childrenActivity.activityType == "achievement")
                itemActivityBinding.item = childrenActivity.toCustomizeNoteAchievementMeds(R.drawable.img_achievement,childrenActivity.txtDescription)
            if(childrenActivity.activityType == "photo")
                itemActivityBinding.item = childrenActivity.toCustomizePhoto(R.drawable.img_photo,studentName)
            if(childrenActivity.activityType == "video")
                itemActivityBinding.item = childrenActivity.toCustomizeVideo(R.drawable.img_video,studentName)
            if(childrenActivity.activityType == "name_to_face")
                itemActivityBinding.item = childrenActivity.toCustomizeNameToFace(R.drawable.img_name_to_face,childrenActivity.txtDescription)
            if(childrenActivity.activityType == "potty")
                itemActivityBinding.item = childrenActivity.toCustomizePotty(R.drawable.img_potty,childrenActivity.txtDescription)
            if(childrenActivity.activityType == "custom")
                itemActivityBinding.item = childrenActivity.toCustomize(R.drawable.img_custom)
            if(childrenActivity.activityType == "incidents")
                itemActivityBinding.item = childrenActivity.toCustomizeIncidents(R.drawable.img_incidents,childrenActivity.txtDescription)
            if(childrenActivity.activityType == "health_check")
                itemActivityBinding.item = childrenActivity.toCustomizeHealthCheck(R.drawable.img_health_check,
                    childrenActivity.temperature,childrenActivity.txtDescription)
            itemActivityBinding.executePendingBindings()
        }
    }
    class SpecificChildDiffUtil : DiffUtil.ItemCallback<ChildrenActivity>(){
        override fun areItemsTheSame(oldItem: ChildrenActivity, newItem: ChildrenActivity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChildrenActivity, newItem: ChildrenActivity): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificRoomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SpecificRoomViewHolder(LayoutItemActivityBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: SpecificRoomViewHolder, position: Int) {
        holder.bind(getItem(position),studentName)

    }
}