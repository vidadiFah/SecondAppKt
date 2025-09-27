package com.example.secondappkt.ui.on_board

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.secondappkt.R
import com.example.secondappkt.data.models.OnBoardModel
import com.example.secondappkt.databinding.FragmentOnBoardBinding
import com.example.secondappkt.data.local.pref.Pref
import com.example.secondappkt.ui.on_board.adapter.OnBoardAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var adapter: OnBoardAdapter
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        pref = Pref(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OnBoardAdapter(loadOnBoardData())
        binding.vpOnBoard.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.vpOnBoard)


        binding.vpOnBoard.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position != (adapter.itemCount - 1)){
                    binding.btnStart.visibility = View.GONE
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.tvSkip.setOnClickListener {
                        onSkipBoard()
                    }
                } else {
                    binding.btnStart.visibility = View.VISIBLE
                    binding.tvSkip.visibility = View.INVISIBLE
                    binding.btnStart.setOnClickListener {
                        onStartBoard()
                    }
                }
            }
        })
    }


    private fun onStartBoard(){
        pref.saveOnBoardBool(true)
        findNavController().navigate(R.id.action_onBoardFragment_to_authFragment)
    }

    private fun onSkipBoard(){
        binding.vpOnBoard.currentItem = 2
    }

    fun loadOnBoardData(): List<OnBoardModel> {
        return listOf(
            OnBoardModel(
                title = "Удобство",
                desc = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.",
                img = "https://i.ibb.co/mrbFYGRB/572298cb925f5eb4f5fe0c07adda040bd75b2727.gif"
            ),
            OnBoardModel(
                title = "Организация",
                desc = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.",
                img = "https://i.ibb.co/WN9GC8c1/ecee4655a706adaaf21259444457321bd362df20.gif"
            ),
            OnBoardModel(
                title = "Синхронизация",
                desc = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте.",
                img = "https://i.ibb.co/XZDqfvr8/5bc021c2b705755e5a87fa01bc1de6f9a54c7e17.gif"
            )
        )
    }
}