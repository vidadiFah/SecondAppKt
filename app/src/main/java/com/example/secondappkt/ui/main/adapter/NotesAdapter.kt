package com.example.secondappkt.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secondappkt.data.models.NoteModel
import com.example.secondappkt.databinding.ItemNoteBinding
import com.example.secondappkt.utils.formatDate

class NotesAdapter(val onLongClick:(NoteModel)-> Unit, val onClick:(NoteModel) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notesList = arrayListOf<NoteModel>()

    fun addAllNotes(list: List<NoteModel>){
        notesList.clear()
        notesList.addAll(list)
        notifyDataSetChanged()
    }


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
            ivNoteBg.setColorFilter(noteModel.color)

            tvDate.formatDate(noteModel.dateCreated)

            itemView.setOnLongClickListener {
                onLongClick(noteModel)
                true
            }

            itemView.setOnClickListener {
                onClick(noteModel)
            }
        }
    }
}