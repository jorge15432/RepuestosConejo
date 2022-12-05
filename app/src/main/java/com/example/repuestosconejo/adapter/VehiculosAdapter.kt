package com.example.repuestosconejo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.repuestosconejo.databinding.VehiculosFilaBinding
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.ui.Pedidos.PedidosFragmentDirections
import com.example.repuestosconejo.ui.Vehiculos.VehiculosFragmentDirections

class VehiculosAdapter:RecyclerView.Adapter<VehiculosAdapter.VehiculosViewHolder>() {

    private var listaVehiculos = emptyList<Vehiculos>()


    inner class VehiculosViewHolder(private val itemBinding: VehiculosFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun dibuja(vehiculos: Vehiculos) {
            itemBinding.tvMarca.text=vehiculos.marca
            itemBinding.tvAno.text=vehiculos.año
            itemBinding.tvModelo.text=vehiculos.modelo
            itemBinding.tvMotor.text=vehiculos.motor
            itemBinding.vistaFila.setOnClickListener{
                val accion= VehiculosFragmentDirections
                    .actionNavVehiculosToAddVehiculos(vehiculos)
                itemView.findNavController().navigate(accion)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculosViewHolder {
        val itemBinding=VehiculosFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                , parent
                ,false)
                return VehiculosViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VehiculosViewHolder, position: Int) {
        val repuestosActual = listaVehiculos[position]
        holder.dibuja(repuestosActual)
    }

    override fun getItemCount(): Int {
        return listaVehiculos.size

    }

    fun setVehiculos(vehiculos: List<Vehiculos>) {
        listaVehiculos = vehiculos
        notifyDataSetChanged()
    }
}