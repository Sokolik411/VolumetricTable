package ru.vsokolova.volumetric_table.ui.volume_table

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import ru.vsokolova.volumetric_table.Dependencies
import ru.vsokolova.volumetric_table.databinding.DialogVolumeTableDataBinding
import ru.vsokolova.volumetric_table.databinding.FragmentVolumetricTableBinding
import ru.vsokolova.volumetric_table.db.chips_data.ChipObject


class VolumeTableFragment : Fragment() {

    private var _binding: FragmentVolumetricTableBinding? = null
    private val viewModel by lazy { AddDataDialogViewModel(Dependencies.volumeRepository) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val volumeTableViewModel =
//            ViewModelProvider(this).get(VolumeTableViewModel::class.java)

        _binding = FragmentVolumetricTableBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chipAdd: Button = binding.chipAdd
        chipAdd.setOnClickListener {
            AddDataDialogFragment().show(
                childFragmentManager,
                AddDataDialogFragment.TAG
            )
        }

        viewModel.chipsDataValue.observe(viewLifecycleOwner) {
            println(it.volume)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addChips(volume: String, ){

    }
}