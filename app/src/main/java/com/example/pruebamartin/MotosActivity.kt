package com.example.pruebamartin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebamartin.databinding.ActivityMotosBinding

class MotosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMotosBinding


    private lateinit var botonHome: ImageButton
    private lateinit var botonGuardados: ImageButton
    private lateinit var botonChistes: ImageButton
    private lateinit var botonAjustes: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        intiEvent()
    }

    private fun intiEvent() {
        botonHome = binding.imageButtonHome
        botonHome.setOnClickListener {
                view->
            intent = Intent(this, MainActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonChistes = binding.imageButtonChistes
        botonChistes.setOnClickListener {
                view->
            intent = Intent(this, ChistesActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonAjustes = binding.imageButtonAjustes
        botonAjustes.setOnClickListener {
                view->
            intent = Intent(this, AjustesActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonGuardados = binding.imageButtonGuardado
        botonGuardados.setOnClickListener {
                view->
            intent = Intent(this, GuardadosActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }
    }


}