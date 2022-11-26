package com.proyecto.appejemplomascotas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.appejemplomascotas.databinding.FragmentVeterinariosBinding

class VeterinariosFragment: Fragment(R.layout.fragment_veterinarios) {

    private var _binding: FragmentVeterinariosBinding? = null
    private val binding get() = _binding!!
    private var lista:MutableList<Veterinario> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVeterinariosBinding.inflate(inflater)
        var view: FrameLayout = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista.add(Veterinario("Dr. Sergio Gómez","3102546879","Bogotá D.C",R.drawable.mascotas))
        lista.add(Veterinario("Dr. Daniel Gómez","3124579562","Bogotá D.C",R.drawable.my_baths))
        lista.add(Veterinario("Dr. Alejandro Martinez","3184786325","Bogotá D.C",R.drawable.ic_nativ_app_foreground))
        lista.add(Veterinario("Dr. Andrea Carrillo","3105421458","Bogotá D.C",R.drawable.ic_nativ_app_foreground))
        lista.add(Veterinario("Dr. Jaqueline Urrutia","3104569874","Bogotá D.C",R.drawable.ic_nativ_app_foreground))
        binding.listaVeterinarios.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerVeterinarioAdapter(lista)
        }
    }
}