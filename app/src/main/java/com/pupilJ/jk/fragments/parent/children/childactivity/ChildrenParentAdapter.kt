package com.pupilJ.jk.fragments.parent.children.childactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pupilJ.domain.models.ChildrenActivity
import com.pupilJ.domain.models.toCustomize
import com.pupilJ.domain.models.toCustomizeFood
import com.pupilJ.domain.models.toCustomizeHealthCheck
import com.pupilJ.domain.models.toCustomizeIncidents
import com.pupilJ.domain.models.toCustomizeNameToFace
import com.pupilJ.domain.models.toCustomizeNap
import com.pupilJ.domain.models.toCustomizeNoteAchievementMeds
import com.pupilJ.domain.models.toCustomizePhoto
import com.pupilJ.domain.models.toCustomizePotty
import com.pupilJ.domain.models.toCustomizeVideo
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.LayoutItemActivityBinding

class ChildrenParentAdapter(private val studentName:String) : ListAdapter<ChildrenActivity, ChildrenParentAdapter.SpecificRoomViewHolder>(SpecificChildDiffUtil()) {

    class SpecificRoomViewHolder(private val itemActivityBinding: LayoutItemActivityBinding):RecyclerView.ViewHolder(itemActivityBinding.root){
        fun bind(childrenActivity: ChildrenActivity, studentName: String) {
            if (childrenActivity.activityType == "food")
                itemActivityBinding.item = childrenActivity.toCustomizeFood(R.drawable.img_food)

            if (childrenActivity.activityType == "nap")
                itemActivityBinding.item = childrenActivity.toCustomizeNap(
                    R.drawable.img_nap,
                    "$studentName ${childrenActivity.napType}"
                )
            if (childrenActivity.activityType == "note")
                itemActivityBinding.item = childrenActivity.toCustomizeNoteAchievementMeds(
                    R.drawable.img_note,
                    childrenActivity.txtDescription
                )
            if (childrenActivity.activityType == "observation")
                itemActivityBinding.item = childrenActivity.toCustomize(R.drawable.img_observation)
            if (childrenActivity.activityType == "meds")
                itemActivityBinding.item = childrenActivity.toCustomizeNoteAchievementMeds(
                    R.drawable.img_med,
                    childrenActivity.txtDescription
                )

            if (childrenActivity.activityType == "achievement") {
                itemActivityBinding.imgItemActivityTeacher.visibility = View.VISIBLE
                itemActivityBinding.item = childrenActivity.toCustomizeNoteAchievementMeds(
                    R.drawable.img_achievement,
                    childrenActivity.txtDescription
                )
            }
            if (childrenActivity.activityType == "photo") {
                itemActivityBinding.imgItemActivityTeacher.visibility = View.VISIBLE
                itemActivityBinding.item = childrenActivity.toCustomizePhoto(
                    R.drawable.img_photo,
                    childrenActivity.image,
                    studentName
                )
            }
            if (childrenActivity.activityType == "video") {
                itemActivityBinding.videoItemActivityTeacher.visibility = View.VISIBLE
                itemActivityBinding.item = childrenActivity.toCustomizeVideo(
                    R.drawable.img_video,
                    studentName,
                    childrenActivity.video
                )
            }
            if (childrenActivity.activityType == "name_to_face")
                itemActivityBinding.item = childrenActivity.toCustomizeNameToFace(
                    R.drawable.img_name_to_face,
                    childrenActivity.txtDescription
                )


            if (childrenActivity.activityType == "potty")
                itemActivityBinding.item = childrenActivity.toCustomizePotty(
                    R.drawable.img_potty,
                    childrenActivity.txtDescription
                )


            if (childrenActivity.activityType == "custom")
                itemActivityBinding.item = childrenActivity.toCustomize(R.drawable.img_custom)


            if (childrenActivity.activityType == "incident")
                itemActivityBinding.item = childrenActivity.toCustomizeIncidents(
                    R.drawable.img_incidents,
                    childrenActivity.txtDescription
                )


            if (childrenActivity.activityType == "health_check")
                itemActivityBinding.item = childrenActivity.toCustomizeHealthCheck(
                    R.drawable.img_health_check,
                    childrenActivity.temperature, childrenActivity.txtDescription
                )

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