package com.example.repuestosconejo.ui.Vehiculos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.repuestosconejo.R
import com.example.repuestosconejo.databinding.FragmentAddVehiculosBinding
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.viewmodel.VehiculosViewModel


class AddVehiculosFragment : Fragment() {
    private var _binding: FragmentAddVehiculosBinding? = null
    private val binding get() = _binding!!
    private lateinit var vehiculosViewModel: VehiculosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vehiculosViewModel = ViewModelProvider(this).get(VehiculosViewModel::class.java)
        _binding = FragmentAddVehiculosBinding.inflate(inflater, container, false)

        binding.btnGuardarVehiculo.setOnClickListener {
            addVehiculo()

        }

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    private fun addVehiculo() {
        val marca = binding.etMarca.text.toString()
        val año = binding.etAO.text.toString()
        val modelo = binding.etModelo.text.toString()
        val motor = binding.etMotor.text.toString()
        if (marca.isNotEmpty()) {

            val vehiculo = Vehiculos(0, marca, año, modelo, motor)
            vehiculosViewModel.saveVehiculos(vehiculo)
            Toast.makeText(
                requireContext(), getString(R.string.msg_vehiculo_added),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addVehiculosFragment_to_nav_vehiculos)

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