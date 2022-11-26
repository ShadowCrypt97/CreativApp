package com.proyecto.appejemplomascotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.proyecto.appejemplomascotas.databinding.ActivityDetalleVeterinarioBinding
import com.proyecto.appejemplomascotas.databinding.FragmentVeterinariosBinding

class DetalleVeterinarioActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetalleVeterinarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleVeterinarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("nombreVet")
        val ciudad = intent.getStringExtra("ciudadVet")
        val celular = intent.getStringExtra("celularVet")
        val foto = intent.getStringExtra("fotoUrlVet")
        val perfil = intent.getStringExtra("perfilVet")

        Glide
            .with(this)
            .load(foto)
            .into(binding.fotoVet)
        binding.nombreVeterinario.text = nombre
        binding.ciudad.text = ciudad
        binding.telefono.text = "Contacto: $celular"
        binding.perfil.text = perfil
    }
}