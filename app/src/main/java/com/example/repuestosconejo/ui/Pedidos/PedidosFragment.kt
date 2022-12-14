package com.example.repuestosconejo.ui.Pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repuestosconejo.R
import com.example.repuestosconejo.adapter.PedidosAdapter
import com.example.repuestosconejo.databinding.FragmentPedidosBinding
import com.example.repuestosconejo.viewmodel.PedidosViewModel

class PedidosFragment : Fragment() {
    private var _binding: FragmentPedidosBinding? = null
    private val binding get() = _binding!!
    private lateinit var pedidosViewModel:PedidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pedidosViewModel = ViewModelProvider(this).get(PedidosViewModel::class.java)
        _binding = FragmentPedidosBinding.inflate(inflater, container, false)


        binding.addpedidos.setOnClickListener{
            findNavController().navigate(R.id.action_nav_pedidos_to_addPedidos)
        }

        val pedidosAdapter=PedidosAdapter()
        val reciclador = binding.recicladorpedidos
        reciclador.adapter=pedidosAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        pedidosViewModel.getPedidos.observe(viewLifecycleOwner){
                pedidos->pedidosAdapter.setPedidos(pedidos)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}