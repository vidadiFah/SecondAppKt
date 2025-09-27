package com.example.secondappkt.ui.on_board.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secondappkt.data.models.OnBoardModel
import com.example.secondappkt.databinding.ItemOnBoardBinding
import com.example.secondappkt.utils.loadImg

class OnBoardAdapter(private val onBoardList: List<OnBoardModel>): RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnBoardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: OnBoardViewHolder,
        position: Int
    ) {
        holder.bind(onBoardList[position])
    }

    override fun getItemCount(): Int {
        return onBoardList.size
    }


    inner class OnBoardViewHolder(private val binding: ItemOnBoardBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(onBoardModel: OnBoardModel){
            binding.ivOnBoard.loadImg(onBoardModel.img)
            binding.tvTitleOnBoard.text = onBoardModel.title
            binding.tvDescOnBoard.text = onBoardModel.desc
        }
    }
}