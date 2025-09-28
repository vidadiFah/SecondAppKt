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
    var btnPickColorPressed = true


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

        arrowBack()


        var color: Int = ContextCompat.getColor(requireContext(), R.color.yellow_note)


        if (note != null) {
            binding.etTitle.setText(note.title)
            binding.etDesc.setText(note.desc)
            color = note.color
            binding.tvOnCreateDate.formatDate(note.dateCreated)
        } else {
            simpleDialogColor()
            binding.tvOnCreateDate.formatDate(System.currentTimeMillis())
        }


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

        binding.dialogColorPicker.visibility = View.GONE
        binding.btnReady.visibility = View.GONE
        binding.ivPickColor.setOnClickListener {
            if (btnPickColorPressed) {
                binding.ivPickColor.setColorFilter(orange)
                binding.dialogColorPicker.visibility = View.VISIBLE
                btnPickColorPressed = false
            } else {
                closeColorDialog(grayDark)
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
            closeColorDialog(grayDark)
        }
        binding.vPurple.setOnClickListener {
            color = purple
            closeColorDialog(grayDark)
        }
        binding.vRose.setOnClickListener {
            color = rose
            closeColorDialog(grayDark)
        }
        binding.vRed.setOnClickListener {
            color = red
            closeColorDialog(grayDark)
        }
        binding.vGreen.setOnClickListener {
            color = green
            closeColorDialog(grayDark)
        }
        binding.vBlue.setOnClickListener {
            color = blue
            closeColorDialog(grayDark)
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

    fun closeColorDialog(color: Int) {
        binding.ivPickColor.setColorFilter(color)
        binding.dialogColorPicker.visibility = View.GONE
        btnPickColorPressed = true
    }

    fun arrowBack(){
        binding.ivArrowLeft.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun simpleDialogColor(){
        binding.vColorLine2.visibility = View.VISIBLE
        binding.dialogColorPicker.maxHeight = 300
        binding.btnDeleteOnCreate.visibility = View.GONE
        binding.tvDelete.visibility = View.GONE
        binding.vColorLine.visibility = View.GONE
    }

}