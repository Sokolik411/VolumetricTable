package ru.vsokolova.volumetric_table.ui.gost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vsokolova.volumetric_table.R
import ru.vsokolova.volumetric_table.databinding.FragmentGostBinding

class GostFragment : Fragment() {

    private var _binding: FragmentGostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ImageArrayAdapter(requireActivity(), resources.obtainTypedArray(R.array.gostPages))
        binding.listviewGost.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}