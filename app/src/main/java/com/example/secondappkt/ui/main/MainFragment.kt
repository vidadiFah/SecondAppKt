package com.example.secondappkt.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.secondappkt.App
import com.example.secondappkt.R
import com.example.secondappkt.data.models.NoteModel
import com.example.secondappkt.databinding.FragmentMainBinding
import com.example.secondappkt.ui.main.adapter.NotesAdapter

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val adapter = NotesAdapter(::onLongNoteClick)
    var isLayoutChanged: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initView()
        setupListener()

        binding.btnChangeLayout.setOnClickListener {
            if(isLayoutChanged){
                binding.rvNotes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                isLayoutChanged = false
                binding.btnChangeLayout.setImageResource(R.drawable.ic_linear_view)
            } else {
                binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
                isLayoutChanged = true
                binding.btnChangeLayout.setImageResource(R.drawable.ic_grid)
            }
        }
    }

    private fun setupListener() {
        binding.fbCreate.setOnClickListener {
            findNavController().navigate(R.id.createFragment)
        }
    }

    private fun initView() {
        binding.rvNotes.adapter = adapter
    }

    private fun loadData() {
        val list: List<NoteModel> = App.db.dao().getAllNote()
        adapter.addAllNotes(list)
    }

    private fun onLongNoteClick(notes: NoteModel){
        App.db.dao().deleteNote(notes)
        loadData()
    }
}
