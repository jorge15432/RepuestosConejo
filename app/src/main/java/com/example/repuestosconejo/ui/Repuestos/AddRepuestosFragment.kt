package com.example.repuestosconejo.ui.Repuestos


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
import com.example.repuestosconejo.model.Repuestos
import com.example.repuestosconejo.model.Vehiculos
import com.example.repuestosconejo.viewmodel.RepuestosViewModel
import com.example.repuestosconejo.viewmodel.VehiculosViewModel


class AddRepuestosFragment : Fragment() {
    private var _binding: FragmentAddRepuestosconejoBinding? = null
    private val binding get() = _binding!!
    private lateinit var repuestosViewModel: RepuestosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repuestosViewModel = ViewModelProvider(this).get(RepuestosViewModel::class.java)
        _binding = FragmentAddRepuestosconejoBinding.inflate(inflater, container, false)

        binding.btnGuardarRepuesto.setOnClickListener {
            addRepuesto()

        }

        return binding.root
    }

    private fun addRepuesto() {
        val nombre = binding.etNombre.text.toString()
        val descripcion = binding.etDescripcion.text.toString()
        val cantidad = binding.etCantidad.text.toString()
        val precio = binding.etPrecio.text.toString()
        if (nombre.isNotEmpty()) {

            val repuesto = Repuestos("", nombre,descripcion,cantidad,precio)
            repuestosViewModel.saveRepuestos(repuesto)
            Toast.makeText(
                requireContext(), getString(R.string.msg_repuesto_added),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addRepuestosconejoFragment_to_nav_repuestos)

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