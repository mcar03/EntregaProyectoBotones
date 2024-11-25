package com.example.pruebamartin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebamartin.databinding.ActivityMainBinding
import com.example.pruebamartin.databinding.ActivityTelefonoBinding

class TelefonoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityTelefonoBinding
    private lateinit var botonLlamada: Button
    private lateinit var botonVolver: Button
    private var numeroTelefono: String = ""
    private lateinit var introduceNumero: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTelefonoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        botonLlamada = binding.buttonLlamar
        introduceNumero = binding.editTextPhone
        botonVolver = binding.buttonVolver
        initEvent()
    }

    private fun initEvent() {

        botonLlamada.setOnClickListener {
            numeroTelefono = introduceNumero.text.toString()
            if (numeroTelefono.isNotEmpty()) {
                requestPermissions()
            } else {
                Toast.makeText(this, "Introduce un telefono", Toast.LENGTH_SHORT).show()
            }
        }

        botonVolver.setOnClickListener {
                view->
            intent = Intent(this, MainActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionPhone()) {
                call()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        } else {
            call()
        }
    }

    private fun permissionPhone(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            call()
        } else {
            Toast.makeText(
                this, "Se necesita habilitar los permisos para llamar",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun call() {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$numeroTelefono")
        }
        startActivity(intent)
    }
}