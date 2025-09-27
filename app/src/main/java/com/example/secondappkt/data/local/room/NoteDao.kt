package com.example.secondappkt.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.secondappkt.data.models.NoteModel

@Dao
interface NoteDao {
    @Query ("SELECT * FROM note_list")
    fun getAllNote(): List<NoteModel>

    @Insert
    fun addNote(taskModel: NoteModel)
}