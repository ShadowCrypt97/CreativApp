package com.proyecto.appejemplomascotas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.proyecto.appejemplomascotas.databinding.FragmentMascotasBinding

class MascotasFragment : Fragment(R.layout.fragment_mascotas) {
    private var _binding: FragmentMascotasBinding? = null
    private val binding get() = _binding!!
    private var fab: FloatingActionButton? = null
    private var lista:MutableList<Mascota> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMascotasBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(context,RegistrarMascotaActivity::class.java)
        /*if(arguments != null){
            val email = requireArguments().getString("email")
            intent.putExtra("email",email)
        }*/
        fab = binding.buttonAgregar
        fab!!.setOnClickListener {
            startActivity(intent)
        }
        crearMascota("Kira","Perro",4,R.drawable.mascotas)

    }

    private fun crearMascota(nombre:String, tipoMascota: String,edad:Int,foto:Int){
        lista.add(Mascota("nombre: $nombre","tipo mascota: $tipoMascota","edad: $edad años",foto))
        binding.listaMascotas.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerMascotaAdapter(lista)
        }
    }
}