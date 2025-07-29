package com.example.glowup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glowup.databinding.ActivityHomeBinding
import com.example.glowup.fragments.HomeFragment
import com.example.glowup.fragments.CartFragment
import com.example.glowup.fragments.ProfileFragment
import androidx.fragment.app.Fragment




class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Iniciar com o fragmento da Home
        replaceFragment(HomeFragment())

        // Configurar os cliques do menu
        binding.menuHome.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        binding.menuCart.setOnClickListener {
            replaceFragment(CartFragment())
        }

        binding.menuProfile.setOnClickListener {
            replaceFragment(ProfileFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

