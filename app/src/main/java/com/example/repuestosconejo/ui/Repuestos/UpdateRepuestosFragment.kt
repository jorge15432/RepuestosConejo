package com.example.repuestosconejo.ui.Repuestos

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
import com.example.repuestosconejo.UpdateRepuestosArgs
import com.example.repuestosconejo.databinding.FragmentUpdateRepuestosBinding
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.viewmodel.RepuestosViewModel

class UpdateRepuestosFragment : Fragment() {

    private val args by navArgs<UpdateRepuestosArgs>()

    private var _binding: FragmentUpdateRepuestosBinding? = null
    private val binding get() = _binding!!
    private lateinit var repuestosViewModel: RepuestosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repuestosViewModel = ViewModelProvider(this)[RepuestosViewModel::class.java]
        _binding = FragmentUpdateRepuestosBinding.inflate(inflater, container, false)


        //Se pasan los valores a los campos de la pantalla
        binding.etNombre.text = args.repuestos.nombre.toString()
        binding.etCantidad.text = args.repuestos.cantidad.toString()
        binding.etDescripcion.text = args.repuestos.descripcion.toString()
        binding.etPrecio.text = args.repuestos.precio.toString()



        binding.btnActualizarRepuesto.setOnClickListener{updateRepuestos()}
        binding.btnEliminarRepuesto.setOnClickListener{deleteRepuesto()}





        return binding.root
    }



    private fun deleteRepuesto() {
        val alerta= AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_repuestos)
        alerta.setMessage(getString(R.string.msg_preguta_eliminar)+"${args.repuestos.nombre}?")
        alerta.setPositiveButton(getString(R.string.msg_sir)){_,_ ->
            repuestosViewModel.deleteRepuestos(args.repuestos)
            Toast.makeText(requireContext(),getString(R.string.bt_delete_repuestos), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateRepuestos_to_nav_repuestos)

        }
        alerta.setNegativeButton(getString(R.string.msg_nor)) {_,_ ->}
        alerta.create().show()
    }

    private fun updateRepuestos() {
        val nombre=binding.etNombre.text.toString()
        val cantidad=binding.etCantidad.text.toString()
        val descripcion=binding.etDescripcion.text.toString()
        val precio=binding.etPrecio.text.toString()
        if(nombre.isNotEmpty()){//Al menos tenemos un nombre
            val repuestos= Repuestos(args.repuestos.id,nombre,descripcion,cantidad,precio)

            repuestosViewModel.saveRepuestos(repuestos)
            Toast.makeText(requireContext(),getString(R.string.msg_repuesto_update),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateRepuestos_to_nav_repuestos)
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