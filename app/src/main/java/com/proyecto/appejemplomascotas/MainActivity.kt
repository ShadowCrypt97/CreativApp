package com.proyecto.appejemplomascotas

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.appejemplomascotas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var relativeLayout:RelativeLayout
    lateinit var animationDrawable:AnimationDrawable
    lateinit var binding:ActivityMainBinding //Manejar los elementos de la vista
    lateinit var  handler: Handler //Manejar los tiempos de la vista
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //traer todos los componentes de la pantalla
        setContentView(R.layout.activity_main)
        setContentView(binding.root) //Root es el relative layout en el xml

        handler = Handler(Looper.myLooper()!!)//Looper my looper para decirle que no me muestre advertencia de deprecated

        handler.postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },10000)

        relativeLayout = findViewById(R.id.main_container)
        animationDrawable = relativeLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()
    }
}