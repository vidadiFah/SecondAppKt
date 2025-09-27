package com.example.secondappkt.ui.create

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondappkt.App
import com.example.secondappkt.R
import com.example.secondappkt.data.models.NoteModel
import com.example.secondappkt.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReady.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDesc.text.toString()
            App.db.dao().addNote(NoteModel(title = title, desc = desc))
            findNavController().navigateUp()
        }




        binding.etTitle.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {}

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                val orange = ContextCompat.getColor(requireContext(), R.color.orange)
                val grayDark = ContextCompat.getColor(requireContext(), R.color.gray_dark)
                if (binding.etTitle.length() > 0 || binding.etDesc.length() > 0){
                    binding.ivPickColor.setColorFilter(orange)

                } else {
                    binding.ivPickColor.setColorFilter(grayDark)
                }
            }

        })

        binding.btnReady.visibility = View.GONE
    }
}