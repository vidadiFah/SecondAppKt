package com.example.secondappkt.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.secondappkt.data.models.NoteModel

@Dao
interface NoteDao {
    @Query ("SELECT * FROM note_list ORDER BY id DESC")
    fun getAllNote(): List<NoteModel>

//    @Query("""
//    SELECT * FROM list_task
//    WHERE date BETWEEN :startInclusive AND :endInclusive
//    ORDER BY date DESC
//""")
//    fun getByDateRange(startInclusive: Date, endInclusive: Date): List<TaskModel>

    @Insert
    fun addNote(noteModel: NoteModel)

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Update
    fun updateNote(noteModel: NoteModel)
}