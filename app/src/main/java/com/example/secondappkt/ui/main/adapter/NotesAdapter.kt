package com.example.secondappkt.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secondappkt.data.models.NoteModel
import com.example.secondappkt.databinding.ItemNoteBinding

class NotesAdapter(val notesList: List<NoteModel>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        return NotesViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount() = notesList.size

    inner class NotesViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteModel: NoteModel) = with(binding) {
            tvTitle.text = noteModel.title
            tvDesc.text = noteModel.desc
        }
    }
}