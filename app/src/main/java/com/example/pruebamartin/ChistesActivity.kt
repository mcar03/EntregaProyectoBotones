package com.example.pruebamartin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebamartin.databinding.ActivityChistesBinding
import java.util.Locale


class ChistesActivity : AppCompatActivity(){

    private lateinit var binding: ActivityChistesBinding

    private lateinit var botonReproducir : ImageButton
    private lateinit var botonHome:ImageButton
    private lateinit var botonGuardados:ImageButton
    private lateinit var botonChistes:ImageButton
    private lateinit var botonAjustes:ImageButton

    private lateinit var textToSpeech: TextToSpeech  //descriptor de voz
    private val TOUCH_MAX_TIME = 500 // en milisegundos
    private var touchLastTime: Long = 0  //para saber el tiempo entre toque.
    private var touchNumber = 0   //numero de toques dado (por si acaso). De momento no nos hace falta.
    private lateinit var handler: Handler
    val MYTAG = "LOGCAT"  //para mirar logs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        configureTextToSpeech()
        initHander()
        initEvent()
    }

    private fun initEvent() {

        val chistes = mutableListOf(
            "¿Por qué los pájaros no usan Facebook? Porque ya tienen Twitter.",
            "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!",
            "¿Por qué el libro de matemáticas estaba triste? Porque tenía demasiados problemas.",
            "¿Cómo se despiden los químicos? Ácido un placer.",
            "¿Qué le dice una impresora a otra? Ese papel es tuyo o es una copia.",
            "¿Por qué la escoba está feliz? ¡Porque va barriendo éxitos!",
            "¿Qué hace una computadora en la playa? ¡Surf en la red!",
            "¿Cómo se llama un boomerang que no vuelve? ¡Un palo!",
            "¿Por qué los esqueletos no pelean entre sí? Porque no tienen agallas.",
            "¿Qué hace una mosca sin alas? Camina."
        )



        botonReproducir.setOnClickListener {
            //Sacamos el tiempo actual
            val currentTime = System.currentTimeMillis()
            //Comprobamos si el margen entre pulsación, da lugar a una doble pulsación.
            if (currentTime - touchLastTime < TOUCH_MAX_TIME){
                    // Doble pulsación detectada, seleccionamos un chiste aleatorio
                    val randomChiste = chistes.random()  // selecciona un chiste aleatorio
                    executorDoubleTouch(randomChiste)  // Reproducimos el chiste
                    touchNumber = 0  // Reiniciamos el contador de pulsaciones
                    Log.i(MYTAG, "Escuchamos el chiste: $randomChiste")

            } else {
                touchNumber = 1  // Primera pulsación
                Log.i(MYTAG, "Hemos pulsado 1 vez.")
                speakMeDescription("Botón para escuchar un chiste")
            }
            touchLastTime = currentTime
            /*  if (touchNumber == 2) {
                  Log.i(MYTAG,"Detectamos 2 pulsaciones.")
                  touchNumber = 0
              }
  */
        }  //fin listener

        botonChistes = binding.imageButtonChistes
        botonChistes.setOnClickListener{
                view->
            intent = Intent(this, ChistesActivity::class.java).apply{
                putExtra("name", "martin")
            }
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

    }

    //Habla
    private fun speakMeDescription(s: String) {
        Log.i(MYTAG,"Intenta hablar")
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun executorDoubleTouch(chiste: String) {
        speakMeDescription(chiste)
        // Toast.makeText(this,"doble pulsacion-> Ejecuto la acción",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        //Si hemos inicializado la propiedad textToSpeech, es porque existe.
        if (::textToSpeech.isInitialized){
            textToSpeech.stop()
            textToSpeech.shutdown()

        }
        super.onDestroy()
    }



    private fun initHander() {
        handler = Handler(Looper.getMainLooper())  //queremos que el tema de la IU, la llevemos al hilo principal.
        binding.progressBar.visibility = View.VISIBLE  //hacemos visible el progress

        botonReproducir = binding.imageButtonReproducir
        botonReproducir.visibility = View.GONE  //ocultamos el botón.

        Thread{
            Thread.sleep(3000)
            handler.post{
                binding.progressBar.visibility = View.GONE  //ocultamos el progress
                val description = getString(R.string.describe)
                speakMeDescription(description)  //que nos comente de qué va esto...
                Log.i(MYTAG,"Se ejecuta correctamente el hilo")
                binding.imageButtonReproducir.visibility = View.VISIBLE

            }
        }.start()
    }

    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it != TextToSpeech.ERROR){
                textToSpeech.language = Locale("es", "ES")
                // textToSpeech.setSpeechRate(1.0f)
                Log.i(MYTAG,"Sin problemas en la configuración TextToSpeech")
            }else{
                Log.i(MYTAG,"Error en la configuración TextToSpeech")
            }
        })
    }

}

