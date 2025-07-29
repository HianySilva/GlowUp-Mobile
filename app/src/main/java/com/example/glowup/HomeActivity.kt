package com.example.glowup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glowup.databinding.ActivityHomeBinding
import com.example.glowup.fragments.HomeFragment
import com.example.glowup.fragments.CartFragment
import com.example.glowup.fragments.AboutUsFragment
import androidx.fragment.app.Fragment




class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragment(HomeFragment())


        binding.menuHome.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        binding.menuCart.setOnClickListener {
            replaceFragment(CartFragment())
        }

        binding.menuAboutUs.setOnClickListener {
            replaceFragment(AboutUsFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

