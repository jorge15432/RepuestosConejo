@file:Suppress("UsePropertyAccessSyntax")

package com.example.repuestosconejo.ui.Pedidos

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.repuestosconejo.R
import com.example.repuestosconejo.UpdatePedidosArgs
import com.example.repuestosconejo.UpdateRepuestosArgs
import com.example.repuestosconejo.UpdateVehiculosArgs
import com.example.repuestosconejo.databinding.FragmentUpdatePedidosBinding
import com.example.repuestosconejo.databinding.FragmentUpdateRepuestosBinding
import com.example.repuestosconejo.databinding.FragmentUpdateVehiculosBinding
import com.example.repuestosconejo.model.Pedidos
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.viewmodel.PedidosViewModel
import com.example.repuestosconejo.viewmodel.RepuestosViewModel
import com.example.repuestosconejo.viewmodel.VehiculosViewModel

class UpdatePedidosFragment : Fragment() {

    private val args by navArgs<UpdatePedidosArgs>()

    private var _binding: FragmentUpdatePedidosBinding? = null
    private val binding get() = _binding!!
    private lateinit var pedidosViewModel: PedidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pedidosViewModel = ViewModelProvider(this).get(PedidosViewModel::class.java)
        _binding = FragmentUpdatePedidosBinding.inflate(inflater, container, false)


        binding.etNombreCliente.setText(args.pedidos.nombre)
        binding.etPrimerApellido.setText(args.pedidos.apellido1)
        binding.etSegundoApellido.setText( args.pedidos.apellido2)
        binding.etDireccion.setText(args.pedidos.direccion)
        binding.etFecha.setText(args.pedidos.fecha)
        binding.etPrecioPedido.setText(args.pedidos.precio)



        binding.btnActualizarPedido.setOnClickListener{updatePedidos()}
        binding.btnEliminarPedido.setOnClickListener{deletePedidos()}





        return binding.root
    }



    private fun deletePedidos() {
        val alerta= AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_pedidos)
        alerta.setMessage(getString(R.string.msg_preguta_eliminar_pedidos)+"${args.pedidos.nombre}?")
        alerta.setPositiveButton(getString(R.string.msg_sip)){_,_ ->
            pedidosViewModel.deletePedidos(args.pedidos)
            Toast.makeText(requireContext(),getString(R.string.msg_pedidos_delete), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updatePedidos_to_nav_pedidos)

        }
        alerta.setNegativeButton(getString(R.string.msg_nop)) {_,_ ->}
        alerta.create().show()
    }

    private fun updatePedidos() {
        val nombre=binding.etNombreCliente.text.toString()
        val primerapellido=binding.etPrimerApellido.text.toString()
        val segundoapellido=binding.etSegundoApellido.text.toString()
        val direccion=binding.etDireccion.text.toString()
        val fecha=binding.etFecha.text.toString()
        val precio=binding.etPrecioPedido.text.toString()
        if(nombre.isNotEmpty()){//Al menos tenemos un nombre
            val pedidos= Pedidos(args.pedidos.id,nombre, primerapellido,segundoapellido,fecha,direccion,precio)

            pedidosViewModel.savePedidos(pedidos)
            Toast.makeText(requireContext(),getString(R.string.msg_pedido_update),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatePedidos_to_nav_pedidos)
        }else{//No hay info del lugar...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}