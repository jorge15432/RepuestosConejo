package com.example.repuestosconejo.ui.Repuestos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repuestosconejo.R
import com.example.repuestosconejo.adapter.RepuestosAdapter
import com.example.repuestosconejo.databinding.FragmentRepuestosBinding
import com.example.repuestosconejo.viewmodel.RepuestosViewModel

class RepuestosFragment : Fragment() {
    private var _binding: FragmentRepuestosBinding? = null
    private val binding get() = _binding!!
    private lateinit var repuestosViewModel:RepuestosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repuestosViewModel = ViewModelProvider(this).get(RepuestosViewModel::class.java)
        _binding = FragmentRepuestosBinding.inflate(inflater, container, false)

        binding.addrepuestos.setOnClickListener{
            findNavController().navigate(R.id.action_nav_repuestos_to_addRepuestosconejoFragment)
        }

        val repuestosAdapter=RepuestosAdapter()
        val reciclador=binding.recicladorrepuestos
        reciclador.adapter=repuestosAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        repuestosViewModel.getRepuestos.observe(viewLifecycleOwner){
                repuestos->repuestosAdapter.setRepuestos(repuestos)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}