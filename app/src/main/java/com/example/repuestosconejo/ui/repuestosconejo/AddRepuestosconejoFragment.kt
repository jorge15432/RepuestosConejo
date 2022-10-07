package com.example.repuestosconejo.ui.repuestosconejo

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
import com.example.repuestosconejo.model.RepuestosConejo
import com.example.repuestosconejo.viewmodel.RepuestosConejoViewModel


class AddRepuestosconejoFragment : Fragment() {
    private var _binding: FragmentAddRepuestosconejoBinding? = null
    private val binding get() = _binding!!
    private lateinit var repuestosConejoViewModel: RepuestosConejoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repuestosConejoViewModel = ViewModelProvider(this).get(RepuestosConejoViewModel::class.java)
        _binding = FragmentAddRepuestosconejoBinding.inflate(inflater, container, false)

        binding.btAddRepuesto.setOnClickListener {
            addRepuesto()

        }

        return binding.root
    }

    private fun addRepuesto() {
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreoRepuesto.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if (nombre.isNotEmpty()) {

            val repuesto = RepuestosConejo(0, nombre, correo, telefono, web)
            //RepuestosConejoViewModel.saveRepuestosConejo(repuesto)
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

