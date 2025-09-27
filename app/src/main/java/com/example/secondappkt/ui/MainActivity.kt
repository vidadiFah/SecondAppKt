package com.example.secondappkt.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.secondappkt.R
import com.example.secondappkt.data.local.pref.Pref
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var pref: Pref
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        pref = Pref(this)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        val navGraph = navHost.navController.navInflater.inflate(R.navigation.nav_host)

        navGraph.setStartDestination(
            if (pref.getOnBoardBool()) {
                if (auth.currentUser != null){
                    R.id.mainFragment
                } else {
                    R.id.authFragment
                }
            } else {
                R.id.onBoardFragment
            }
        )

        navHost.navController.graph = navGraph
    }
}