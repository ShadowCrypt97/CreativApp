package com.proyecto.appejemplomascotas

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.proyecto.appejemplomascotas.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding //Manejar los elementos de la vista

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)
        setContentView(binding.root)
        val navigation = binding.menuNavigation
        val mascotasFragment = MascotasFragment()
        val veterinariosFragment = VeterinariosFragment()
        val myBathsFragment = MyBathsFragment()
        val agendarBathsFragment = AgendarBathsFragment()

        navigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mascotas -> {
                    menuInferior(mascotasFragment)
                    true
                }
                R.id.veterinarios -> {
                    menuInferior(veterinariosFragment)
                    true
                }
                R.id.my_baths -> {
                    menuInferior(myBathsFragment)
                    true
                }
                R.id.agendar_baths -> {
                    menuInferior(agendarBathsFragment)
                    true
                }
                else -> false
            }
        }
    }
    private fun menuInferior(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment_container_view, fragment)
            commit()
        }
    }
}