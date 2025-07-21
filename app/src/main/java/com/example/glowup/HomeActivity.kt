package com.example.glowup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glowup.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProdutos.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        binding.btnBuscar.setOnClickListener {
            showSearchDialog()
        }

        setupCategoryClicks()
    }

    private fun setupCategoryClicks() {
        val categoryMap = mapOf(
            binding.categoryBlush to "Blush",
            binding.categoryBronzer to "Bronzer",
            binding.categoryEyebrow to "Sobrancelhas",
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
                val intent = Intent(this, CategoryActivity::class.java).apply {
                    putExtra("CATEGORY_NAME", name)
                }
                startActivity(intent)
            }
        }
    }

    private fun showSearchDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Buscar Produtos")
            .setMessage("Funcionalidade de busca será implementada aqui")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}
