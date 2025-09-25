package com.example.secondappkt.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.secondappkt.core.AppKey

class Pref(context: Context) {
    val pref: SharedPreferences = context.getSharedPreferences(AppKey.APP_KEY, Context.MODE_PRIVATE)

    fun saveOnBoardBool(onBoardBool: Boolean){
        pref.edit {
            putBoolean(AppKey.ON_BOARD_KEY, onBoardBool)
        }
    }

    fun getOnBoardBool(): Boolean{
        return pref.getBoolean(AppKey.ON_BOARD_KEY, false)
    }

}