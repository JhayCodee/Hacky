package com.hawk.hacky.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.hawk.hacky.R
import com.hawk.hacky.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // viewBinding
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    // Fragments
    private var homeFragment = HomeFragment()
    private var cursosFragment = CursosFragment()
    private var canalesFragment = CanalesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigationView
        setupWithNavController(bottomNavigationView, navController)



    }

}