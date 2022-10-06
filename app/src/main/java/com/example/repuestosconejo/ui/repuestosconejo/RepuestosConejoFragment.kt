package com.example.repuestosconejo.ui.repuestosconejo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.repuestosconejo.databinding.FragmentRepuestosConejoBinding
import com.example.repuestosconejo.viewmodel.RepuestosConejoViewModel

class RepuestosConejoFragment : Fragment() {

    private var _binding: FragmentRepuestosConejoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repuestosConejoViewModel =
            ViewModelProvider(this).get(RepuestosConejoViewModel::class.java)

        _binding = FragmentRepuestosConejoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}