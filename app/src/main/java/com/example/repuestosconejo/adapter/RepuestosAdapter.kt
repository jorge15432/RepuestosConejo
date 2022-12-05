package com.example.repuestosconejo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repuestosconejo.databinding.RepuestosFilaBinding
import com.example.repuestosconejo.model.Repuestos

class RepuestosAdapter:RecyclerView.Adapter<RepuestosAdapter.RepuestosViewHolder> ()  {



    private var listaRepuestos = emptyList<Repuestos>()


    inner class RepuestosViewHolder(private val itemBinding: RepuestosFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun dibuja(repuestos: Repuestos) {
            itemBinding.tvNombre.text = repuestos.nombre
            itemBinding.tvDescripcion.text = repuestos.descripcion
            itemBinding.tvCantidad.text = repuestos.cantidad
            itemBinding.tvPrecio.text = repuestos.precio


        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepuestosViewHolder {
        val itemBinding=RepuestosFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                , parent
                ,false)
        return RepuestosViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RepuestosViewHolder, position: Int) {
        val repuestosActual = listaRepuestos[position]
        holder.dibuja(repuestosActual)
    }

    override fun getItemCount(): Int {
        return listaRepuestos.size

    }

    fun setRepuestos(repuestos: List<Repuestos>) {
        listaRepuestos = repuestos
        notifyDataSetChanged()
    }
}