package com.proyecto.appejemplomascotas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.proyecto.appejemplomascotas.databinding.FragmentVeterinariosBinding

class VeterinariosFragment: Fragment(R.layout.fragment_veterinarios) {

    private var _binding: FragmentVeterinariosBinding? = null
    private val binding get() = _binding!!
    private var lista:MutableList<Veterinario> = mutableListOf()
    lateinit var firestoreBD: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firestoreBD = FirebaseFirestore.getInstance()
        _binding = FragmentVeterinariosBinding.inflate(inflater)
        var view: FrameLayout = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista.clear()
        firestoreBD.collection("Vetrinarios").get().addOnSuccessListener{
            result ->
                for (doc in result){
                    val vet = Veterinario(
                        doc.getString("nombres") as String,
                        doc.getString("celular")as String,
                        doc.getString("ciudad") as String,
                        doc.getString("fotoUrl") as String
                    )
                    lista.add(vet)
                }
            binding.listaVeterinarios.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = RecyclerVeterinarioAdapter(lista)
            }
        }
    }
}