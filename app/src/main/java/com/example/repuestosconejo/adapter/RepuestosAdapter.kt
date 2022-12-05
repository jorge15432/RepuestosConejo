package com.example.repuestosconejo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.repuestosconejo.databinding.RepuestosFilaBinding
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.ui.Repuestos.AddRepuestosFragmentDirections
import com.example.repuestosconejo.ui.Repuestos.RepuestosFragmentDirections

class RepuestosAdapter:RecyclerView.Adapter<RepuestosAdapter.RepuestosViewHolder> ()  {



    //La lista de lugares a "dibujar"
    private var listaRepuestos= emptyList<Repuestos>()

    //Contenedor de vistas "cajitas" en memoria...
    inner class RepuestosViewHolder(private val itemBinding: RepuestosFilaBinding)
        : RecyclerView.ViewHolder(itemBinding.root){

        fun dibuja(repuestos: Repuestos){
            itemBinding.tvNombre.text=repuestos.nombre
            itemBinding.tvCantidad.text=repuestos.cantidad
            itemBinding.tvDescripcion.text=repuestos.descripcion
            itemBinding.tvPrecio.text=repuestos.precio

            itemBinding.vistaFila.setOnClickListener{
                val accion=RepuestosFragmentDirections
                    .actionNavRepuestosToUpdateRepuestos(repuestos)
                itemView.findNavController().navigate(accion)
            }
        }

    }

    //Crea una "cajita" una vista del tipo lugarFila...
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepuestosViewHolder {
        val itemBinding=RepuestosFilaBinding
            .inflate(LayoutInflater.from(parent.context)
                ,parent
                ,false)
        return RepuestosViewHolder(itemBinding)
    }

    //Con una "cajita" creada... se pasa a dibujar los datos del lugar x
    override fun onBindViewHolder(holder: RepuestosViewHolder, position: Int) {
        val repuestoActual=listaRepuestos[position]
        holder.dibuja(repuestoActual)
    }

    override fun getItemCount(): Int {
        return listaRepuestos.size
    }

    fun setRepuestos(repuestos:List<Repuestos>){
        listaRepuestos=repuestos
        notifyDataSetChanged() //Se notifica que el conjunto de datos cambio y se redibuja toda la informacion
    }
}