package com.example.secondappkt.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.secondappkt.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): NoteDao
}