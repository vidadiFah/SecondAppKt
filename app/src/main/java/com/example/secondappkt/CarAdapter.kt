package com.example.secondappkt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secondappkt.databinding.ItemCarBinding

class CarAdapter(val carList: ArrayList<CarModel>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarViewHolder {
        return CarViewHolder(ItemCarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(
        holder: CarViewHolder,
        position: Int
    ) {
        holder.onBind(carList[position])
    }

    override fun getItemCount(): Int {
        return carList.size
    }


    class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(model: CarModel) {
            binding.tvName.text = model.title
            binding.tvPrice.text = model.price.toString()
            binding.tvShape.text = model.shape.toString()
            binding.tvTransmission.text = model.transmission.toString()
            binding.tvConditioner.text = model.ac.toString()
            binding.ivCar.setImageResource(model.imageResId)         }
    }
}