package com.example.secondappkt.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.secondappkt.AppKey

class Pref(context: Context) {
    val pref: SharedPreferences = context.getSharedPreferences(AppKey.app_key, Context.MODE_PRIVATE)

    fun saveOnBoardBool(onBoardBool: Boolean){
        pref.edit {
            putBoolean(AppKey.on_board_key, onBoardBool)
        }
    }

    fun getOnBoardBool(): Boolean{
        return pref.getBoolean(AppKey.on_board_key, false)
    }

}