package com.example.repuestosconejo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.repuestosconejo.databinding.PedidosFilaBinding
import com.example.repuestosconejo.model.Pedidos
import com.example.repuestosconejo.ui.Pedidos.PedidosFragmentDirections

class PedidosAdapter:RecyclerView.Adapter<PedidosAdapter.PedidosViewHolder>() {

    private var listaPedidos = emptyList<Pedidos>()


    inner class PedidosViewHolder(private val itemBinding: PedidosFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun dibuja(pedidos: Pedidos) {
            itemBinding.tvNombre.text = pedidos.nombre
            itemBinding.tvApellido1.text = pedidos.apellido1
            itemBinding.tvApellido2.text = pedidos.apellido2
            itemBinding.tvFecha.text = pedidos.fecha
            itemBinding.tvDireccion.text = pedidos.direccion
            itemBinding.tvPrecio.text = pedidos.precio
            itemBinding.vistaFila.setOnClickListener{
                val accion=PedidosFragmentDirections
                    .actionNavPedidosToUpdatePedidos(pedidos)
                itemView.findNavController().navigate(accion)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder {
        val itemBinding=PedidosFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                , parent
                ,false)
                return PedidosViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val pedidosActual = listaPedidos[position]
        holder.dibuja(pedidosActual)
    }

    override fun getItemCount(): Int {
        return listaPedidos.size

    }

    fun setPedidos(pedidos: List<Pedidos>) {
        listaPedidos = pedidos
        notifyDataSetChanged()
    }
}