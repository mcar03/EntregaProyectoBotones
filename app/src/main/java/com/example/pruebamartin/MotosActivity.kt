package com.example.pruebamartin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebamartin.controller.Controller
import com.example.pruebamartin.databinding.ActivityMotosBinding

class MotosActivity : AppCompatActivity() {
    lateinit var binding: ActivityMotosBinding
    lateinit var controller: Controller

    private lateinit var botonHome: ImageButton
    private lateinit var botonGuardados: ImageButton
    private lateinit var botonChistes: ImageButton
    private lateinit var botonAjustes: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        intiEvent()
        initEventRecycler()
    }

    private fun initEventRecycler() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)

        controller = Controller(this)
        controller.setAdapter()  // Esta función se encargará de poner el adaptador
    }

    private fun intiEvent() {
        botonHome = binding.imageButtonHome
        botonHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonChistes = binding.imageButtonChistes
        botonChistes.setOnClickListener {
            val intent = Intent(this, ChistesActivity::class.java).apply {
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonAjustes = binding.imageButtonAjustes
        botonAjustes.setOnClickListener {
            val intent = Intent(this, AjustesActivity::class.java).apply {
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonGuardados = binding.imageButtonGuardado
        botonGuardados.setOnClickListener {
            val intent = Intent(this, GuardadosActivity::class.java).apply {
                putExtra("name", "martin")
            }
            startActivity(intent)
        }
    }
}
