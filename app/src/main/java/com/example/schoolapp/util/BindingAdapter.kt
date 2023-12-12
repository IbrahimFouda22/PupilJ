package com.example.schoolapp.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.schoolapp.R
import com.squareup.picasso.Picasso

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView,url:String){
    Picasso.get().load(url).into(imageView)
}

@BindingAdapter("bindImageLogo")
fun bindImageLogo(imageView: ImageView,id:Int){
    imageView.setImageResource(id)
}

@BindingAdapter("bindTextAttend")
fun bindTextAttend(textView: TextView,attended: Boolean){
    if(attended){
        textView.text = textView.context.getString(R.string.check_in)
        textView.setBackgroundColor(textView.context.getColor(R.color.colorAttended))
    }
    else{
        textView.text = textView.context.getString(R.string.check_out)
        textView.setBackgroundColor(textView.context.getColor(R.color.colorNotAttended))
    }
}

@BindingAdapter("bindImageCorrect")
fun bindImageCorrect(imageView: ImageView,selected: Boolean){
    Log.d("children", selected.toString())

    if(selected){
        imageView.visibility = View.VISIBLE

    }
    else{
        imageView.visibility = View.INVISIBLE
    }
}

@BindingAdapter("bindSrcCorrect")
fun bindSrcCorrect(imageView: ImageView,selected: Boolean){
    if(selected){
        imageView.alpha = 0.2f
    }
    else{
        imageView.alpha = 1f
    }
}