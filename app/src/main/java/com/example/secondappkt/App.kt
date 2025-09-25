package com.example.secondappkt

import android.app.Application
import androidx.room.Room
import com.example.secondappkt.data.local.room.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database_note")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}