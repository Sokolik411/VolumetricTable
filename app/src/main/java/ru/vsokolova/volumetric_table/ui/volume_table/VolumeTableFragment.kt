package ru.vsokolova.volumetric_table.ui.volume_table

import android.R
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import ru.vsokolova.volumetric_table.Dependencies
import ru.vsokolova.volumetric_table.databinding.DialogVolumeTableDataBinding
import ru.vsokolova.volumetric_table.databinding.FragmentVolumetricTableBinding
import ru.vsokolova.volumetric_table.db.chips_data.ChipObject


class VolumeTableFragment : Fragment() {

    private var _binding: FragmentVolumetricTableBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { VolumeTableViewModel() }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.setFragmentResultListener("requestKey", this) { key, bundle ->
            val chipObject = bundle.getSerializable("bundleKey") as ChipObject
            println(chipObject.getVolume() + " "+ chipObject.getAmount() + " " + chipObject.getResult())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVolumetricTableBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addChips(volume: String, ){

    }
}