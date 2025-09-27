package com.example.secondappkt.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImg(img: String) {
    Glide.with(this).load(img).into(this)
}

fun TextView.formatDate(date:Long){
    val formatter = java.text.SimpleDateFormat("dd MMM HH:mm", java.util.Locale.getDefault())
    this.text = formatter.format(java.util.Date(date))
}

fun Context.showToast(text:String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun EditText.listenerEdit(
    afterTextChanged: () -> Unit = {},
    beforeTextChanged: () -> Unit = {},
    onTextChanged: () -> Unit = {},
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged()
        }

        override fun beforeTextChanged(
            p0: CharSequence?,
            p1: Int,
            p2: Int,
            p3: Int
        ) {
            beforeTextChanged()
        }

        override fun onTextChanged(
            p0: CharSequence?,
            p1: Int,
            p2: Int,
            p3: Int
        ) {
            onTextChanged()
        }
    })
}