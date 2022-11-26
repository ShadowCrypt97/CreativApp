package com.proyecto.appejemplomascotas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.proyecto.appejemplomascotas.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding //Manejar los elementos de la vista
    lateinit var firebaseAuth: FirebaseAuth

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
        firebaseAuth = Firebase.auth
        val email = firebaseAuth.currentUser?.email.toString()
        //val email = intent.getStringExtra("email")
        Toast.makeText(this,"Bienvenido $email", Toast.LENGTH_LONG).show()
        /*val bundle = Bundle()
        bundle.putString("email",email)
        mascotasFragment.arguments = bundle*/
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
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}