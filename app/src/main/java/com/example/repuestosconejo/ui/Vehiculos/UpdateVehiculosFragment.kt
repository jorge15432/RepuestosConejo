package com.example.repuestosconejo.ui.Vehiculos

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
import com.example.repuestosconejo.UpdateVehiculosArgs
import com.example.repuestosconejo.databinding.FragmentUpdateVehiculosBinding
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.viewmodel.VehiculosViewModel

class UpdateVehiculosFragment : Fragment() {

    //Se recupera un argumento pasadp...
    private val args by navArgs<UpdateVehiculosArgs>()

    private var _binding: FragmentUpdateVehiculosBinding? = null
    private val binding get() = _binding!!
    private lateinit var vehiculosViewModel: VehiculosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vehiculosViewModel = ViewModelProvider(this).get(VehiculosViewModel::class.java)
        _binding = FragmentUpdateVehiculosBinding.inflate(inflater, container, false)


        //Se pasan los valores a los campos de la pantalla
        binding.etMarca.setText(args.vehiculos.marca)
        binding.etModelo.setText(args.vehiculos.modelo)
        binding.etAO.setText(args.vehiculos.año)
        binding.etMotor.setText(args.vehiculos.motor)



        binding.btnActualizarVehiculo.setOnClickListener{updateVehiculo()}
        binding.btnEliminarVehiculo.setOnClickListener{deleteVehiculo()}





        return binding.root
    }



    private fun deleteVehiculo() {
        val alerta= AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_vehiculos)
        alerta.setMessage(getString(R.string.msg_pregunta_eliminar)+"${args.vehiculos.marca}?")
        alerta.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            vehiculosViewModel.deleteVehiculos(args.vehiculos)
            Toast.makeText(requireContext(),getString(R.string.msg_vehiculo_deleted), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateVehiculos_to_nav_vehiculos)

        }
        alerta.setNegativeButton(getString(R.string.msg_no)) {_,_ ->}
        alerta.create().show()
    }

    //Efectivamente hace el registro del lugar en la base de datos
    private fun updateVehiculo() {
        val marca=binding.etMarca.text.toString()
        val motor=binding.etMotor.text.toString()
        val año=binding.etAO.text.toString()
        val modelo=binding.etModelo.text.toString()
        if(marca.isNotEmpty()){//Al menos tenemos un nombre
            val vehiculo= Vehiculos(args.vehiculos.id,marca,año,modelo,motor)

            vehiculosViewModel.saveVehiculos(vehiculo)
            Toast.makeText(requireContext(),getString(R.string.msg_vehiculo_updated),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateVehiculos_to_nav_vehiculos)
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