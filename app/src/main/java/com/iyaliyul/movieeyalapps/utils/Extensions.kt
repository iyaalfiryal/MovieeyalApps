package com.iyaliyul.movieeyalapps.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

//extension function
fun ImageView.loadImage(url : String){
    Glide.with(this)
        .load(url)
        .into(this)
}

fun Context.showToast(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        .show()
}

fun getCurrentData(format: String): String{
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(Date().time)
}
