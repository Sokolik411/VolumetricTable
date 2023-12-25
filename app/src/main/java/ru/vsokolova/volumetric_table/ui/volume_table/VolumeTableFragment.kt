package ru.vsokolova.volumetric_table.ui.volume_table

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.vsokolova.volumetric_table.databinding.DialogVolumeTableDataBinding
import ru.vsokolova.volumetric_table.databinding.FragmentVolumetricTableBinding


class VolumeTableFragment : Fragment() {

    private var _binding: FragmentVolumetricTableBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val volumeTableViewModel =
            ViewModelProvider(this).get(VolumeTableViewModel::class.java)

        _binding = FragmentVolumetricTableBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val chipAdd: Button = binding.chipAdd
        chipAdd.setOnClickListener {
            AddDataDialogFragment().show(
                childFragmentManager,
                AddDataDialogFragment.TAG
            )
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}