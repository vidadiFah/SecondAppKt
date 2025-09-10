package com.example.secondappkt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondappkt.data.models.CarModel
import com.example.secondappkt.R
import com.example.secondappkt.databinding.FragmentMainBinding
import com.example.secondappkt.enums.Shape
import com.example.secondappkt.enums.Transmission
import com.example.secondappkt.enums.YesNo

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var list = arrayListOf<CarModel>()

    private lateinit var carAdapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initView()
    }

    private fun initView() {
        carAdapter = CarAdapter(list, ::onClick)
        binding.rvCar.adapter = carAdapter
    }
    fun onClick(carModel: CarModel){
        val action = MainFragmentDirections.actionMainFragmentToDetailCarFragment(carModel)
        findNavController().navigate(action)
    }

    private fun loadData() {
        list = arrayListOf<CarModel>(
            CarModel(
                img = "https://i.ibb.co/6cPRYSYb/image.png",
                title = "BMW series 5",
                price = 200,
                shape = Shape.Sedan,
                transmission = Transmission.Automatic,
                ac = YesNo.Yes
            ),
            CarModel(
                img = "https://i.ibb.co/nNNkqcbd/image6.png",
                title = "Mercedes Sprinter",
                price = 80,
                shape = Shape.Van,
                transmission = Transmission.Manual,
                ac = YesNo.No
            ),
            CarModel(
                img = "https://i.ibb.co/NgQnFsvf/image2.png",
                title = "Honda Fit",
                price = 40,
                shape = Shape.Hatchback,
                transmission = Transmission.Manual,
                ac = YesNo.No
            ),
            CarModel(
                img = "https://i.ibb.co/tM7394nQ/image5.png",
                title = "Hyundai Palisade",
                price = 160,
                shape = Shape.SUV,
                transmission = Transmission.Robot,
                ac = YesNo.Yes
            ),
            CarModel(
                img = "https://i.ibb.co/0RHMD9F7/image8.png",
                title = "Kia Carnival",
                price = 110,
                shape = Shape.Minivan,
                transmission = Transmission.Automatic,
                ac = YesNo.Yes
            ),
            CarModel(
                img = "https://i.ibb.co/BHNvw7MP/image1.png",
                title = "Mercedes W222",
                price = 220,
                shape = Shape.Sedan,
                transmission = Transmission.Automatic,
                ac = YesNo.Yes
            ),
            CarModel(
                img = "https://i.ibb.co/N69j5YWM/image9.png",
                title = "Kia K3",
                price = 130,
                shape = Shape.Sedan,
                transmission = Transmission.Robot,
                ac = YesNo.Yes
            )
        )
    }
}