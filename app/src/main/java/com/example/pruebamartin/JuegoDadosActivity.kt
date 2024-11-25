package com.example.pruebamartin

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebamartin.databinding.ActivityJuegodadosBinding
import com.example.pruebamartin.databinding.ActivityTelefonoBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class JuegoDadosActivity : AppCompatActivity() {
    private var sum : Int = 0

    private lateinit var binding: ActivityJuegodadosBinding
    private lateinit var botonHome:ImageButton
    private lateinit var botonGuardados:ImageButton
    private lateinit var botonComentarios:ImageButton
    private lateinit var botonAjustes:ImageButton

    private lateinit var imagenViewDados1 : ImageView
    private lateinit var imagenViewDados2 : ImageView
    private lateinit var imagenViewDados3 : ImageView


    private lateinit var botonJugar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJuegodadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initEvent()
    }

    private fun initEvent() {

        //BOTON HOME
        botonHome = binding.imageButtonHome
        botonHome.setOnClickListener{
                view->
            intent = Intent(this, MainActivity::class.java).apply{
                putExtra("name", "martin")
            }
            startActivity(intent)
        }

        //BOTON GUARDADOS

        //BOTON COMENTARIOS

        //BOTON AJUSTES

        //BOTON JUGAR

        botonJugar = binding.buttonJugarDados
        botonJugar.setOnClickListener {
            game()
        }

    }

    private fun game() {
        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        val msc = 1000
        for (i in 1..5){//lanzamos 5 veces el dado
            schedulerExecutor.schedule(
                {
                    throwDadoInTime()  //Lanzo los tres dados.
                },
                msc * i.toLong(), TimeUnit.MILLISECONDS)
        }

        schedulerExecutor.schedule({//El último hilo, es mostrar el resultado.
            viewResult()
        },
            msc  * 7.toLong(), TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown()  //Ya no aceptamos más hilos.

    }


    /*
    Método que lanza los tres dados a partir de 3 aleatorios.
     */
    private fun throwDadoInTime() {
        val numDados = Array(3){ Random.nextInt(1, 6)}
        val imagViews : Array<ImageView> = arrayOf<ImageView>(
            binding.imageViewDados1,
            binding.imageViewDados2,
            binding.imageViewDados3)

        sum = numDados.sum() //me quedo con la suma actual
        for (i in 0..3) //cambio las imagenes, a razón de los aleatorios.
            selectView(imagViews[i], numDados[i])

    }


    /*
    Método que dependiendo de la vista, carga una imagen de dado u otro.
     */
    private fun selectView(imgV: ImageView, v: Int) {
        when (v){
            1 -> imgV.setImageResource(R.drawable.dado1);
            2 -> imgV.setImageResource(R.drawable.dado2);
            3 -> imgV.setImageResource(R.drawable.dado3);
            4 -> imgV.setImageResource(R.drawable.dado4);
            5 -> imgV.setImageResource(R.drawable.dado5);
            6 -> imgV.setImageResource(R.drawable.dado6);
        }

    }


    /*
    Muestra los resultados, que es la suma de la última tirada.
     */
    private fun viewResult() {
        binding.textViewResultadoDados.text= sum.toString()
        println(sum)
    }

}