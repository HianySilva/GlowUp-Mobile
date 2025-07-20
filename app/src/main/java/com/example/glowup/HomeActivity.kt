package com.example.glowup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Configurar o botão "VER PRODUTOS"
        val btnProdutos = findViewById<Button>(R.id.btnProdutos)
        btnProdutos.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        // Configurar o botão de busca
        val btnBuscar = findViewById<ImageButton>(R.id.btnBuscar)
        btnBuscar.setOnClickListener {
            showSearchDialog()
        }

        // Configurar os cliques nas categorias
        setupCategoryClicks()
    }

    private fun setupCategoryClicks() {
        // Mapeamento de IDs para nomes de categoria
        val categoryMap = mapOf(
            R.id.category_powder to "Pó Facial",
            R.id.category_lipstick to "Batom",
            R.id.category_mascara to "Rímel",
            R.id.category_eyeliner to "Delineador",
            R.id.category_foundation to "Base",
            R.id.category_eyeshadow to "Sombra",
            R.id.category_blush to "Blush",
            R.id.category_concealer to "Corretivo",
            R.id.category_highlighter to "Iluminador",
            R.id.category_primer to "Primer"
        )

        // Configurar o listener para cada categoria
        categoryMap.forEach { (id, name) ->
            findViewById<FrameLayout>(id).setOnClickListener {
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
