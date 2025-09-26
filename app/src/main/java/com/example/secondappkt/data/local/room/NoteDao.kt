package com.example.secondappkt.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.secondappkt.data.models.NoteModel

@Dao
interface NoteDao {
    @Query ("SELECT * FROM note_list ORDER BY id DESC")
    fun getAllNote(): List<NoteModel>

    @Insert
    fun addNote(noteModel: NoteModel)

    @Delete
    fun deleteNote(noteModel: NoteModel)
}