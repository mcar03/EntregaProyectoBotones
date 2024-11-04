package com.example.pruebamartin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebamartin.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var botonAlarma:ImageButton
    private lateinit var botonLlamada:ImageButton
    private lateinit var botonEmail:ImageButton
    private lateinit var botonMap:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initEvent()
    }

    private fun initEvent() {

        botonLlamada = binding.botonLlamada
        botonLlamada.setOnClickListener{
                view->
            intent = Intent(this, TelefonoActivity::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }

        botonEmail = binding.botonEmail
        botonEmail.setOnClickListener{
            val email = "micorreo@gmail.com"
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No se puede abrir la aplicación de correo.", Toast.LENGTH_SHORT).show()
            }

        }
        botonAlarma = binding.botonAlarma
        botonAlarma.setOnClickListener{
            crearAlarma()
        }

        var urlMap = "https://www.google.com/maps/preview"
        botonMap = binding.botonMap
        botonMap.setOnClickListener{
                view->
            var link = Uri.parse(urlMap)
            intent = Intent(Intent.ACTION_VIEW,link)
            startActivity(intent)

        }
        botonAlarma = binding.botonAlarma
        botonAlarma.setOnClickListener{
            crearAlarma()
        }

    }
    private fun crearAlarma() {
        val alarma = Calendar.getInstance()
        alarma.add(Calendar.MINUTE, 2)
        intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma en 2 minutos")
            putExtra(AlarmClock.EXTRA_HOUR, alarma.get(Calendar.HOUR_OF_DAY))
            putExtra(AlarmClock.EXTRA_MINUTES, alarma.get(Calendar.MINUTE))
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No se puede crear la alarma", Toast.LENGTH_SHORT).show()
        }
    }
}