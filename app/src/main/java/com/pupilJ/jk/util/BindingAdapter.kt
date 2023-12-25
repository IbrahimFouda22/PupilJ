package com.pupilJ.jk.util

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.databinding.BindingAdapter
import com.pupilJ.jk.R
import com.squareup.picasso.Picasso
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, url: String) {
    Picasso.get().load(url).into(imageView)
}

@BindingAdapter("bindDateMessage")
fun bindDateMessage(textView: TextView, date: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val localZonedDateTime = ZonedDateTime.parse(date)
            .withZoneSameInstant(ZoneId.systemDefault())
        textView.text = localZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"))
    } else {
        textView.visibility = View.GONE
    }
}

@BindingAdapter("bindVideo")
fun bindVideo(videoView: VideoView, url: String?) {
    if (!url.isNullOrEmpty()) {
        videoView.setVideoPath(url)
        val mediaController = MediaController(videoView.context)

        mediaController.setAnchorView(videoView)

        mediaController.setMediaPlayer(videoView)

        videoView.setMediaController(mediaController)
    }
}


@BindingAdapter("bindImageLogo")
fun bindImageLogo(imageView: ImageView, id: Int) {
    imageView.setImageResource(id)
}

@BindingAdapter(value = ["checkedIn", "checkedOut"], requireAll = true)
fun bindTextAttend(textView: TextView, checkedIn: Boolean, checkedOut: Boolean) {
    if (!checkedIn && !checkedOut ) {
        textView.visibility = View.GONE
    }
    if (checkedOut) {
        textView.text = textView.context.getString(R.string.check_out)
        textView.setBackgroundColor(textView.context.getColor(R.color.colorNotAttended))
    } else {
        textView.text = textView.context.getString(R.string.check_in)
        textView.setBackgroundColor(textView.context.getColor(R.color.colorAttended))
    }
}

@BindingAdapter("bindImageCorrect")
fun bindImageCorrect(imageView: ImageView, selected: Boolean) {
    Log.d("children", selected.toString())

    if (selected) {
        imageView.visibility = View.VISIBLE

    } else {
        imageView.visibility = View.INVISIBLE
    }
}

@BindingAdapter("bindSrcCorrect")
fun bindSrcCorrect(imageView: ImageView, selected: Boolean) {
    if (selected) {
        imageView.alpha = 0.2f
    } else {
        imageView.alpha = 1f
    }
}

@BindingAdapter("bindLogoItemReminder")
fun bindLogoItemReminder(imageView: ImageView, type: String) {
    if (type == imageView.context.getString(R.string.foodSmall))
        imageView.setImageResource(R.drawable.icon_food_add_reminder)

    if (type == imageView.context.getString(R.string.toiletSmall))
        imageView.setImageResource(R.drawable.icon_toilte_add_reminder)

    if (type == imageView.context.getString(R.string.diberSmall))
        imageView.setImageResource(R.drawable.icon_diber_add_reminder)

    if (type == imageView.context.getString(R.string.noteSmall))
        imageView.setImageResource(R.drawable.icon_notes_add_reminder)

    if (type == imageView.context.getString(R.string.napSmall))
        imageView.setImageResource(R.drawable.icon_nap_add_reminder)

    if (type == imageView.context.getString(R.string.medsSmall))
        imageView.setImageResource(R.drawable.icon_med_add_reminder)
}