package com.example.secondappkt.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.secondappkt.App
import com.example.secondappkt.R
import com.example.secondappkt.data.models.NoteModel
import com.example.secondappkt.databinding.FragmentCreateBinding
import com.example.secondappkt.utils.formatDate
import com.example.secondappkt.utils.listenerEdit

class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding
    val args: CreateFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = args.note

        val formatter = java.text.SimpleDateFormat("dd MMM HH:mm", java.util.Locale.getDefault())
        binding.tvOnCreateDate.formatDate(System.currentTimeMillis())

        var color: Int = ContextCompat.getColor(requireContext(), R.color.yellow_note)

        if (note != null) {
            binding.etTitle.setText(note.title)
            binding.etDesc.setText(note.desc)
            color = note.color
            binding.tvOnCreateDate.formatDate(note.dateCreated)
        }

        val formatter = java.text.SimpleDateFormat("dd MMM HH:mm", java.util.Locale.getDefault())
        binding.tvOnCreateDate.text = formatter.format(java.util.Date(System.currentTimeMillis()))

        var color: Int = ContextCompat.getColor(requireContext(), R.color.yellow_note)
        binding.btnReady.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDesc.text.toString()
            if (note != null) {
                App.db.dao().updateNote(
                    NoteModel(
                        title = title,
                        desc = desc,
                        color = color,
                        id = note.id,
                        dateCreated = note.dateCreated
                    )
                )
            } else {
                App.db.dao().addNote(
                    NoteModel(
                        title = title,
                        desc = desc,
                        color = color,
                        dateCreated = System.currentTimeMillis()
                    )
                )
            }
            findNavController().navigateUp()
        }

        binding.etTitle.listenerEdit(
            onTextChanged = {
                listenerDane()
            },
        )

        binding.etDesc.listenerEdit(
            onTextChanged = {
                listenerDane()
            },
        )


        val orange = ContextCompat.getColor(requireContext(), R.color.orange)
        val grayDark = ContextCompat.getColor(requireContext(), R.color.gray_dark)
        var pickColorPressed = true
        binding.dialogColorPicker.visibility = View.GONE
        binding.btnReady.visibility = View.GONE
        binding.ivPickColor.setOnClickListener {
            if (pickColorPressed) {
                binding.ivPickColor.setColorFilter(orange)
                binding.dialogColorPicker.visibility = View.VISIBLE
                pickColorPressed = false
            } else {
                binding.ivPickColor.setColorFilter(grayDark)
                binding.dialogColorPicker.visibility = View.GONE
                pickColorPressed = true
            }
        }


        val yellow = ContextCompat.getColor(requireContext(), R.color.yellow_note)
        val purple = ContextCompat.getColor(requireContext(), R.color.purple_note)
        val rose = ContextCompat.getColor(requireContext(), R.color.rose_note)
        val red = ContextCompat.getColor(requireContext(), R.color.red_note)
        val green = ContextCompat.getColor(requireContext(), R.color.green_note)
        val blue = ContextCompat.getColor(requireContext(), R.color.blue_note)
        binding.vYellow.setOnClickListener {
            color = yellow
        }
        binding.vPurple.setOnClickListener {
            color = purple
        }
        binding.vRose.setOnClickListener {
            color = rose
        }
        binding.vRed.setOnClickListener {
            color = red
        }
        binding.vGreen.setOnClickListener {
            color = green
        }
        binding.vBlue.setOnClickListener {
            color = blue
        }


        binding.dialogDeleteNoteCreate.visibility = View.GONE
        binding.btnDeleteOnCreate.setOnClickListener {
            val note = args.note
            binding.dialogDeleteNoteCreate.visibility = View.VISIBLE
            binding.vDarkLayer.visibility = View.VISIBLE
            binding.btnDeleteCreateFragDialog.setOnClickListener {
                if (note != null) {
                    App.db.dao().deleteNote(note)
                    findNavController().navigateUp()
                } else {
                    findNavController().navigateUp()
                }
                binding.dialogDeleteNoteCreate.visibility = View.GONE
                binding.vDarkLayer.visibility = View.GONE
            }
            binding.btnDeclineCreateFragDialog.setOnClickListener {
                binding.dialogDeleteNoteCreate.visibility = View.GONE
                binding.vDarkLayer.visibility = View.GONE

            }
        }

    }

    fun listenerDane(){
        if (binding.etTitle.length() > 0 || binding.etDesc.length() > 0) {
            binding.btnReady.visibility = View.VISIBLE
        } else {
            binding.btnReady.visibility = View.GONE
        }
    }
}