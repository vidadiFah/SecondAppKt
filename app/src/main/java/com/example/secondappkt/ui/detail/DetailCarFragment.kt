package com.example.secondappkt.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.secondappkt.data.models.CarModel
import com.example.secondappkt.databinding.FragmentDetialCarBinding
import com.example.secondappkt.utils.loadImg
import kotlin.getValue

class DetailCarFragment : Fragment() {

    private lateinit var binding: FragmentDetialCarBinding
    private val args: DetailCarFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetialCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carModel = args.carModel
        binding.tvShape.text = carModel.shape.toString()
        binding.tvTitle.text = carModel.title
        binding.tvPriceInt.text = carModel.price.toString()
        binding.tvSnow.text = carModel.ac.toString()
        binding.tvTransmission.text = carModel.transmission.toString()
        binding.ivCar.loadImg(carModel.img)
    }
}