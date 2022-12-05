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
import com.example.repuestosconejo.UpdateVehiculosArgs
import com.example.repuestosconejo.databinding.FragmentUpdateRepuestosBinding
import com.example.repuestosconejo.databinding.FragmentUpdateVehiculosBinding
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.viewmodel.RepuestosViewModel
import com.example.repuestosconejo.viewmodel.VehiculosViewModel

class UpdateRepuestosFragment : Fragment() {

    //Se recupera un argumento pasadp...
    private val args by navArgs<UpdateRepuestosArgs>()

    private var _binding: FragmentUpdateRepuestosBinding? = null
    private val binding get() = _binding!!
    private lateinit var repuestosViewModel: RepuestosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repuestosViewModel = ViewModelProvider(this).get(RepuestosViewModel::class.java)
        _binding = FragmentUpdateRepuestosBinding.inflate(inflater, container, false)


        //Se pasan los valores a los campos de la pantalla
        binding.etNombre.setText(args.repuestos.nombre)
        binding.etCantidad.setText(args.repuestos.cantidad)
        binding.etDescripcion.setText(args.repuestos.descripcion)
        binding.etPrecio.setText(args.repuestos.precio)



        binding.btnActualizarRepuesto.setOnClickListener{updateRepuestos()}
        binding.btnEliminarRepuesto.setOnClickListener{deleteRepuesto()}





        return binding.root
    }



    private fun deleteRepuesto() {
        val alerta= AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_repuestos)
        alerta.setMessage(getString(R.string.msg_pregunta_eliminar)+"${args.repuestos.nombre}?")
        alerta.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            repuestosViewModel.deleteRepuestos(args.repuestos)
            Toast.makeText(requireContext(),getString(R.string.msg_vehiculo_deleted), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateRepuestos_to_nav_repuestos)

        }
        alerta.setNegativeButton(getString(R.string.msg_no)) {_,_ ->}
        alerta.create().show()
    }

    //Efectivamente hace el registro del lugar en la base de datos
    private fun updateRepuestos() {
        val nombre=binding.etNombre.text.toString()
        val cantidad=binding.etCantidad.text.toString()
        val descripcion=binding.etDescripcion.text.toString()
        val precio=binding.etPrecio.text.toString()
        if(nombre.isNotEmpty()){//Al menos tenemos un nombre
            val repuestos= Repuestos(args.repuestos.id,nombre,descripcion,cantidad,precio)

            repuestosViewModel.saveRepuestos(repuestos)
            Toast.makeText(requireContext(),getString(R.string.msg_repuesto_updated),
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