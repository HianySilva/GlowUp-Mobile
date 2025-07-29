package com.example.glowup.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.glowup.CategoryActivity
import com.example.glowup.ProductsActivity
import com.example.glowup.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Botão de produtos
        binding.btnProdutos.setOnClickListener {
            val intent = Intent(requireContext(), ProductsActivity::class.java)
            startActivity(intent)
        }

        // Botão de busca
        binding.btnBuscar.setOnClickListener {
            showSearchDialog()
        }

        // Categorias
        setupCategoryClicks()
    }

    private fun setupCategoryClicks() {
        val categoryMap = mapOf(
            binding.categoryBlush to "Blush",
            binding.categoryBronzer to "Bronzer",
            binding.categoryEyebrow to "Lápis de Olho",
            binding.categoryEyeliner to "Delineador",
            binding.categoryEyeshadow to "Sombra",
            binding.categoryFoundation to "Base",
            binding.categoryLipLiner to "Lápis de Boca",
            binding.categoryLipstick to "Batom",
            binding.categoryMascara to "Rímel",
            binding.categoryNailPolish to "Esmalte"
        )

        categoryMap.forEach { (view, name) ->
            view.setOnClickListener {
                val intent = Intent(requireContext(), CategoryActivity::class.java).apply {
                    putExtra("CATEGORY_NAME", name)
                }
                startActivity(intent)
            }
        }
    }

    private fun showSearchDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Buscar Produtos")
            .setMessage("Funcionalidade de busca será implementada aqui")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}