package com.proyecto.appejemplomascotas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerMascotaAdapter(var listaMascotas:MutableList<Mascota>): RecyclerView.Adapter<RecyclerMascotaAdapter.MiHolder>(){

    inner class MiHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var nombre: TextView
        var tipoMascota: TextView
        var edad: TextView
        var foto: ImageView

        init {
            nombre = itemView.findViewById(R.id.nombre_mascota)
            tipoMascota = itemView.findViewById(R.id.tipo_mascota)
            edad = itemView.findViewById(R.id.edad_mascota)
            foto = itemView.findViewById(R.id.foto_mascota)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_mascota,parent,false)
        return MiHolder(itemView)
    }

    override fun onBindViewHolder(holder: MiHolder, position: Int) {
        var mascota = listaMascotas[position]
        holder.nombre.text = mascota.nombre
        holder.tipoMascota.text = mascota.tipoMascota
        holder.edad.text = mascota.edad.toString()
        holder.foto.setImageResource(mascota.foto)
    }

    override fun getItemCount(): Int {
        return listaMascotas.size
    }
}