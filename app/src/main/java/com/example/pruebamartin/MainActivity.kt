package com.example.pruebamartin

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
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
    private lateinit var botonMotos:ImageButton
    private lateinit var botonDados:ImageButton

    private lateinit var botonHome:ImageButton
    private lateinit var botonGuardados:ImageButton
    private lateinit var botonChistes:ImageButton
    private lateinit var botonAjustes:ImageButton



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

        botonLlamada = binding.imageButtonTelefono
        botonLlamada.setOnClickListener{
                view->
            intent = Intent(this, TelefonoActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }
        botonGuardados = binding.imageButtonGuardado
        botonGuardados.setOnClickListener{
                view->
            intent = Intent(this, GuardadosActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }
        botonAjustes = binding.imageButtonAjustes
        botonAjustes.setOnClickListener{
                view->
            intent = Intent(this, AjustesActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonChistes = binding.imageButtonChistes
        botonChistes.setOnClickListener{
                view->
            intent = Intent(this, ChistesActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }
        botonDados = binding.imageButtonJuegoDados
        botonDados.setOnClickListener{
                view->
            intent = Intent(this, JuegoDadosActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        botonEmail = binding.imageButtonEmail
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
        botonAlarma = binding.imageButtonAlarma
        botonAlarma.setOnClickListener{
            crearAlarma()
        }

        var urlMap = "https://www.google.com/maps/preview"
        botonMap = binding.imageButtonMaps
        botonMap.setOnClickListener{
                view->
            var link = Uri.parse(urlMap)
            intent = Intent(Intent.ACTION_VIEW,link)
            startActivity(intent)

        }

        botonHome = binding.imageButtonHome
        botonHome.setOnClickListener{
                view->
            intent = Intent(this, MainActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }
        botonMotos = binding.imageButtonMotos
        botonMotos.setOnClickListener{
                view->
            intent = Intent(this, MotosActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }


    }
    private fun crearAlarma() {
        val alarma = Calendar.getInstance()
        alarma.add(Calendar.MINUTE, 2)  // Establece la alarma para dentro de 2 minutos

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma en 2 minutos") // El mensaje que puede mostrar el sistema
            putExtra(AlarmClock.EXTRA_HOUR, alarma.get(Calendar.HOUR_OF_DAY))
            putExtra(AlarmClock.EXTRA_MINUTES, alarma.get(Calendar.MINUTE))
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)  // Dispara la alarma
            Toast.makeText(this, "Alarma establecida para dentro de 2 minutos", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No se puede crear la alarma", Toast.LENGTH_SHORT).show()
        }
    }




}