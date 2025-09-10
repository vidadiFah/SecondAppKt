package com.example.secondappkt.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondappkt.data.models.CarModel
import com.example.secondappkt.databinding.ItemCarBinding

class CarAdapter(val carList: ArrayList<CarModel>, val onClick: (carModel: CarModel) -> Unit) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarViewHolder {
        return CarViewHolder(
            ItemCarBinding.inflate(
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


    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(model: CarModel) {
            binding.tvName.text = model.title
            binding.tvPrice.text = model.price.toString()
            binding.tvShape.text = model.shape.toString()
            binding.tvTransmission.text = model.transmission.toString()
            binding.tvConditioner.text = model.ac.toString()
            binding.ivCar.loadImg(model.img)

            itemView.setOnClickListener {
                onClick(model)
            }
        }
    }
}

fun ImageView.loadImg(img: String){
    Glide.with(this).load(img).into(this)
}