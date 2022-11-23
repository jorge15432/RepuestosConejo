package com.example.repuestosconejo.ui.Vehiculos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.repuestosconejo.R
import com.example.repuestosconejo.databinding.FragmentAddRepuestosconejoBinding
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.viewmodel.VehiculosViewModel


class AddVehiculosFragment : Fragment() {
    private var _binding: FragmentAddRepuestosconejoBinding? = null
    private val binding get() = _binding!!
    private lateinit var vehiculosViewModel: VehiculosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vehiculosViewModel = ViewModelProvider(this).get(VehiculosViewModel::class.java)
        _binding = FragmentAddRepuestosconejoBinding.inflate(inflater, container, false)

        binding.btnGuardarRepuesto.setOnClickListener {
            addRepuesto()

        }

        return binding.root
    }

    private fun addRepuesto() {
        val marca = binding.etMarca.text.toString()
        val año = binding.etAORep.text.toString()
        val codigo = binding.etCodigo.text.toString()
        //val imagen = binding.imgCarro..toString() Almacenar imagen
        val precio = binding.etPrecio.text.toString()
        val cantidad = binding.etCantidad.text.toString()
        if (codigo.isNotEmpty()) {

            val repuesto = Vehiculos(0, marca, año, codigo, precio, cantidad)
            //VehiculosViewModel.saveRepuestosConejo(repuesto)
            Toast.makeText(
                requireContext(), getString(R.string.msg_repuesto_added),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addRepuestosconejoFragment_to_nav_repuestos_conejo)

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

