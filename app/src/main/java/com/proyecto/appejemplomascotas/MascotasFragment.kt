package com.proyecto.appejemplomascotas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.proyecto.appejemplomascotas.databinding.FragmentMascotasBinding

class MascotasFragment : Fragment(R.layout.fragment_mascotas) {
    private var _binding: FragmentMascotasBinding? = null
    private val binding get() = _binding!!
    private var fab: FloatingActionButton? = null

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
        fab = binding.buttonAgregar
        fab!!.setOnClickListener {
            startActivity(Intent(context, RegistrarMascotaActivity::class.java))
        }
    }
}