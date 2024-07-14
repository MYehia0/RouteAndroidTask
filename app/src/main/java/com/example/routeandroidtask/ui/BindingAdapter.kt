package com.example.routeandroidtask.ui

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.routeandroidtask.R


@BindingAdapter("imageUrl")
fun loadImageUrl(imageView: ImageView, url:String){
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.ic_image)
        .into(imageView)
}

@BindingAdapter("paintFlags")
fun paintFlagsOnText(textView: TextView, flag:Boolean){
    if (flag){
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}