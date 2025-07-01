package com.example.glowup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

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
            // Implemente a lógica de busca aqui
            showSearchDialog()
        }

        // Configurar os cliques nas categorias
        setupCategoryClicks()
    }

    private fun setupCategoryClicks() {
        // IDs dos layouts de cada categoria (assumindo que são os primeiros filhos do GridLayout)
        val gridCategorias = findViewById<GridLayout>(R.id.gridCategorias)

        // Como não temos IDs individuais para cada categoria, vamos acessar pela posição
        val categories = listOf(
            "Pó Facial",
            "Batom",
            "Rímel",
            "Delineador",
            "Base",
            "Sombra"
        )

        // Verifica se o GridLayout tem pelo menos 6 itens (2 colunas x 3 linhas)
        if (gridCategorias.childCount >= 6) {
            for (i in 0 until 6) {
                val categoryLayout = gridCategorias.getChildAt(i)
                categoryLayout.setOnClickListener {
                    val intent = Intent(this, CategoryActivity::class.java).apply {
                        putExtra("CATEGORY_NAME", categories[i])
                    }
                    startActivity(intent)
                }
            }
        }
    }

    private fun showSearchDialog() {
        // Implementação básica de um diálogo de busca
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Buscar Produtos")
            .setMessage("Funcionalidade de busca será implementada aqui")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}