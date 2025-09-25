package com.example.secondappkt.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.secondappkt.R
import com.example.secondappkt.data.local.pref.Pref

class MainActivity : AppCompatActivity() {
    private lateinit var pref: Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val navHost = supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment

        val navGraph = navHost.navController.navInflater.inflate(R.navigation.nav_host)

        pref = Pref(this)

        navGraph.setStartDestination(
            if (pref.getOnBoardBool()) {
                R.id.mainFragment
            } else {
                R.id.onBoardFragment
            }
        )

        navHost.navController.graph = navGraph
    }
}