package com.example.repuestosconejo.ui.Pedidos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.repuestosconejo.R
import com.example.repuestosconejo.databinding.FragmentAddPedidosBinding
import com.example.repuestosconejo.databinding.FragmentAddRepuestosconejoBinding
import com.example.repuestosconejo.model.Pedidos
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.viewmodel.PedidosViewModel
import com.example.repuestosconejo.viewmodel.RepuestosViewModel
import com.example.repuestosconejo.viewmodel.VehiculosViewModel


class AddPedidosFragment : Fragment() {
    private var _binding: FragmentAddPedidosBinding? = null
    private val binding get() = _binding!!
    private lateinit var pedidosViewModel: PedidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pedidosViewModel = ViewModelProvider(this).get(PedidosViewModel::class.java)
        _binding = FragmentAddPedidosBinding.inflate(inflater, container, false)

        binding.btnGuardarPedido.setOnClickListener {
            addPedido()

        }

        return binding.root
    }

    private fun addPedido() {
        val nombre = binding.etNombreCliente.text.toString()
        val apellido1 = binding.etPrimerApellido.text.toString()
        val apellido2 = binding.etSegundoApellido.text.toString()
        val fecha = binding.etFecha.text.toString()
        val direccion = binding.etDireccion.text.toString()
        val precio = binding.etPrecioPedido.text.toString()
        if (nombre.isNotEmpty()) {

            val pedidos = Pedidos(0, nombre,apellido1,apellido2,fecha,direccion,precio)
            pedidosViewModel.savePedidos(pedidos)
            Toast.makeText(
                requireContext(), getString(R.string.msg_repuesto_added),
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_addPedidos_to_nav_pedidos)
        } else {
            Toast.makeText(
                requireContext(), getString(R.string.msg_data),
                Toast.LENGTH_LONG
            ).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}