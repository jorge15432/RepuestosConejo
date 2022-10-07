package com.example.repuestosconejo.ui.repuestosconejo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.repuestosconejo.R
import com.example.repuestosconejo.databinding.FragmentRepuestosConejoBinding
import com.example.repuestosconejo.viewmodel.RepuestosConejoViewModel

class RepuestosConejoFragment : Fragment() {
    private var _binding: FragmentRepuestosConejoBinding? = null
    private val binding get() = _binding!!
    private lateinit var repuestosConejoViewModel:RepuestosConejoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         repuestosConejoViewModel = ViewModelProvider(this).get(RepuestosConejoViewModel::class.java)
        _binding = FragmentRepuestosConejoBinding.inflate(inflater, container, false)

        binding.addrepuestosconejoFabBut.setOnClickListener{
            findNavController().navigate(R.id.action_nav_repuestos_conejo_to_addRepuestosconejoFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}