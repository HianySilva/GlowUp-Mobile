package com.example.glowup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.glowup.CartManager
import com.example.glowup.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)

        val pedidoFinalizado = arguments?.getBoolean("pedido_finalizado") ?: false
        if (pedidoFinalizado) {
            Toast.makeText(requireContext(), "Pedido realizado com sucesso!", Toast.LENGTH_LONG).show()
            CartManager.clearCart()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
