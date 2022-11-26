package com.proyecto.appejemplomascotas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.proyecto.appejemplomascotas.databinding.FragmentMascotasBinding

class MascotasFragment : Fragment(R.layout.fragment_mascotas) {
    private var _binding: FragmentMascotasBinding? = null
    private val binding get() = _binding!!
    private var fab: FloatingActionButton? = null
    private var lista:MutableList<Mascota> = mutableListOf()
    lateinit var firestoreBD:FirebaseFirestore
    lateinit var firebaseAuth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMascotasBinding.inflate(inflater)
        firestoreBD = FirebaseFirestore.getInstance()//permite hacer la conexion con la BD de firestore
        firebaseAuth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista.clear()
        val id = firebaseAuth.currentUser?.uid.toString()
        val intent = Intent(context,RegistrarMascotaActivity::class.java)
        firestoreBD.collection("Usuarios/$id/Mascotas").get().addOnSuccessListener{
                result ->
            for (doc in result){
                val pet = Mascota(
                    doc.getString("nombre") as String,
                    doc.getString("tipoMascota") as String,
                    doc.getString("edad")as String,
                    doc.getString("fotoUrl") as String
                )
                lista.add(pet)
            }
            binding.listaMascotas.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = RecyclerMascotaAdapter(lista)
            }
        }
        /*if(arguments != null){
            val email = requireArguments().getString("email")
            intent.putExtra("email",email)
        }*/
        fab = binding.buttonAgregar
        fab!!.setOnClickListener {
            startActivity(intent)
        }
    }
}