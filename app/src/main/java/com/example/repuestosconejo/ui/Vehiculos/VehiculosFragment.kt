package com.example.repuestosconejo.ui.Vehiculos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repuestosconejo.R
import com.example.repuestosconejo.adapter.VehiculosAdapter
import com.example.repuestosconejo.databinding.FragmentVehiculosBinding
import com.example.repuestosconejo.viewmodel.VehiculosViewModel

class VehiculosFragment : Fragment() {
    private var _binding: FragmentVehiculosBinding? = null
    private val binding get() = _binding!!
    private lateinit var vehiculosViewModel:VehiculosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         vehiculosViewModel = ViewModelProvider(this).get(VehiculosViewModel::class.java)
        _binding = FragmentVehiculosBinding.inflate(inflater, container, false)

        binding.addrepuestosconejoFabBut.setOnClickListener{
            findNavController().navigate(R.id.action_nav_vehiculos_to_addVehiculosFragment)
        }
        val vehiculosAdapter=VehiculosAdapter()
            val reciclador=binding.reciclador
        reciclador.adapter=vehiculosAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        vehiculosViewModel.getVehiculos.observe(viewLifecycleOwner){
                vehiculos->vehiculosAdapter.setVehiculos(vehiculos)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}