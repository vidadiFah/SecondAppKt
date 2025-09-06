package com.example.secondappkt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondappkt.databinding.FragmentMainBinding

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
        carAdapter = CarAdapter(list)
        binding.rvCar.adapter = carAdapter
    }

    private fun loadData() {
        list = arrayListOf<CarModel>(
            CarModel(
                imageResId = R.drawable.ic_bmw_5,
                title = "BMW series 5",
                price = 200,
                shape = Shape.Sedan,
                transmission = Transmission.Automatic,
                ac = YesNo.Yes
            ),
            CarModel(
                imageResId = R.drawable.ic_sprinter,
                title = "Mercedes Sprinter",
                price = 80,
                shape = Shape.Van,
                transmission = Transmission.Manual,
                ac = YesNo.No
            ),
            CarModel(
                imageResId = R.drawable.ic_honda_fit,
                title = "Honda Fit",
                price = 40,
                shape = Shape.Hatchback,
                transmission = Transmission.Manual,
                ac = YesNo.No
            ),
            CarModel(
                imageResId = R.drawable.ic_hyun_palisad,
                title = "Hyundai Palisade",
                price = 160,
                shape = Shape.SUV,
                transmission = Transmission.Robot,
                ac = YesNo.Yes
            ),
            CarModel(
                imageResId = R.drawable.ic_kia_carnival,
                title = "Kia Carnival",
                price = 110,
                shape = Shape.Minivan,
                transmission = Transmission.Automatic,
                ac = YesNo.Yes
            ),
            CarModel(
                imageResId = R.drawable.ic_mers_w222,
                title = "Mercedes W222",
                price = 220,
                shape = Shape.Sedan,
                transmission = Transmission.Automatic,
                ac = YesNo.Yes
            ),
            CarModel(
                imageResId = R.drawable.ic_kia_k3,
                title = "Kia K3",
                price = 130,
                shape = Shape.Sedan,
                transmission = Transmission.Robot,
                ac = YesNo.Yes
            )
        )
    }
}