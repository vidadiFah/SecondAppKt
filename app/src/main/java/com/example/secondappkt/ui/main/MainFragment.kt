package com.example.secondappkt.ui.main

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.secondappkt.databinding.FragmentMainBinding
import com.example.secondappkt.App
import com.example.secondappkt.R
import com.example.secondappkt.data.models.NoteModel
import com.example.secondappkt.ui.main.adapter.NotesAdapter
import com.example.secondappkt.utils.listenerEdit
import com.example.secondappkt.utils.loadImg
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val auth: FirebaseAuth = Firebase.auth
    private val adapter = NotesAdapter(::onLongNoteClick, ::onClick)
    private var pickedDate: Calendar? = null
    private var pickedTime: Pair<Int, Int>? = null
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
        setupLister()


        val userName: String = auth.currentUser?.displayName.toString()
        binding.tvUserName.text = userName
        binding.ivUserAvatar.loadImg(auth.currentUser?.photoUrl.toString())
        binding.btnMenu.setOnClickListener {
            binding.vDarkLayerMenu.visibility = View.VISIBLE
            val animator = ObjectAnimator.ofFloat(binding.conMenu, "translationX", -1000f, 0f)
            animator.duration = 400
            binding.conMenu.visibility = View.VISIBLE
            animator.start()
        }
        binding.vDarkLayerMenu.setOnClickListener {
            binding.vDarkLayerMenu.visibility = View.GONE
            val animator = ObjectAnimator.ofFloat(binding.conMenu, "translationX", 0f, -1000f)
            animator.duration = 400
            binding.conMenu.visibility = View.GONE
            animator.start()
        }
        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
            googleSignInClient.signOut()
            requireActivity().finishAffinity()
        }

        binding.btnCalendar.setOnClickListener {
            val cal = Calendar.getInstance()

            val datePicker = DatePickerDialog(
                requireContext(),
                R.style.MyDatePickerStyle,
                { _, year, month, dayOfMonth ->
                    if (pickedDate == null) pickedDate = Calendar.getInstance()
                    pickedDate?.set(year, month, dayOfMonth, 0, 0, 0)
                    pickedDate?.set(Calendar.MILLISECOND, 0)

                    searchNotesByDate(pickedDate!!.timeInMillis, ignoreTime = true)
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        binding.etSearchNote.listenerEdit (onTextChanged={
            if (binding.etSearchNote.length() > 0) {
                binding.tvSearchNote.visibility = View.GONE
            } else {
                binding.tvSearchNote.visibility = View.VISIBLE
            }
        } )

        binding.dialogDeleteNoteMain.visibility = View.GONE
        binding.vDarkLayer.animate().alpha(0f)

        binding.btnChangeLayout.setOnClickListener {
            if (isLayoutChanged) {
                binding.rvNotes.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                isLayoutChanged = false
                binding.btnChangeLayout.setImageResource(R.drawable.ic_linear_view)
            } else {
                binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
                isLayoutChanged = true
                binding.btnChangeLayout.setImageResource(R.drawable.ic_grid)
            }
        }
    }

    private fun setupLister() {
        binding.fbCreate.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCreateFragment(null)
            findNavController().navigate(action)
        }
    }

    private fun initView() {
        binding.rvNotes.adapter = adapter
    }

    private fun loadData() {
        val list: List<NoteModel> = App.db.dao().getAllNote()
        adapter.addAllNotes(list)
    }

    private fun onLongNoteClick(notes: NoteModel) {
        binding.dialogDeleteNoteMain.visibility = View.VISIBLE
        binding.vDarkLayer.visibility = View.VISIBLE
        binding.btnDeleteMainFragDialog.setOnClickListener {
            App.db.dao().deleteNote(notes)
            binding.dialogDeleteNoteMain.visibility = View.GONE
            binding.vDarkLayer.visibility = View.GONE
            loadData()
        }
        binding.btnDeclineMainFragDialog.setOnClickListener {
            binding.dialogDeleteNoteMain.visibility = View.GONE
            binding.vDarkLayer.visibility = View.GONE
            loadData()
        }
    }

    private fun onClick(notes: NoteModel) {
        val action = MainFragmentDirections.actionMainFragmentToCreateFragment(notes)
        findNavController().navigate(action)
    }

    private fun searchNotesByDate(pickedTime: Long, ignoreTime: Boolean) {
        val filteredList = App.db.dao().getAllNote().filter { note ->
            if (ignoreTime) {
                val calNote = Calendar.getInstance().apply { timeInMillis = note.dateCreated }
                val calPick = Calendar.getInstance().apply { timeInMillis = pickedTime }
                calNote.get(Calendar.YEAR) == calPick.get(Calendar.YEAR) &&
                        calNote.get(Calendar.MONTH) == calPick.get(Calendar.MONTH) &&
                        calNote.get(Calendar.DAY_OF_MONTH) == calPick.get(Calendar.DAY_OF_MONTH)
            } else {
                val startCal = Calendar.getInstance().apply { timeInMillis = pickedTime }
                startCal.set(Calendar.SECOND, 0)
                startCal.set(Calendar.MILLISECOND, 0)
                val startTime = startCal.timeInMillis

                startCal.set(Calendar.SECOND, 59)
                startCal.set(Calendar.MILLISECOND, 999)
                val endTime = startCal.timeInMillis

                note.dateCreated in startTime..endTime
            }
        }
        adapter.addAllNotes(filteredList)
    }

}