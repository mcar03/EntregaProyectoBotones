package com.example.pruebamartin

import android.os.Bundle
import android.widget.CheckBox
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebamartin.databinding.ActivityAjustesBinding


class AjustesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAjustesBinding

    private lateinit var switchModoOscuro : Switch
    private lateinit var checkRecibirOfertas: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAjustesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initEvent()
    }

    private fun initEvent() {

        switchModoOscuro = binding.switchModoOscuro
        switchModoOscuro.setOnCheckedChangeListener{ _, isChecked ->

            if (switchModoOscuro.isChecked){
                binding.root.setBackgroundColor(getColor(R.color.black))
            }else{
                binding.root.setBackgroundColor(ContextCompat.getColor(this,R.color.white))

            }
        }

        checkRecibirOfertas = binding.checkBoxRecibirOfertas
        checkRecibirOfertas.setOnCheckedChangeListener{_, isChecked ->
                if (switchModoOscuro.isActivated){
                    Toast.makeText(this,"Deniegas la política de la empresa",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Aceptas la política de la empresa",Toast.LENGTH_SHORT).show()

                }

        }
    }
}