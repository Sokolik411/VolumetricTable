package ru.vsokolova.volumetric_table.ui.volume_table

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.vsokolova.volumetric_table.Dependencies
import ru.vsokolova.volumetric_table.databinding.DialogVolumeTableDataBinding


class AddDataDialogFragment : DialogFragment() {

    private var _binding: DialogVolumeTableDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { AddDataDialogViewModel(Dependencies.volumeRepository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogVolumeTableDataBinding.inflate(layoutInflater)
        val root: View = binding.root


        val checkboxTrunkApex = binding.checkboxTrunkApex

        val textViewLength: MaterialAutoCompleteTextView = binding.textViewLength
        textViewLength.addTextChangedListener {
            viewModel.getLengths(it.toString(), checkboxTrunkApex.isChecked)
        }

        viewModel.lengths.observe(
            this
        ) { lengthArray ->
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, lengthArray.orEmpty())
            adapter.filter.filter(textViewLength.text.toString())
            textViewLength.setAdapter(adapter)
        }

        val textViewThick: MaterialAutoCompleteTextView = binding.textViewThick
        textViewThick.addTextChangedListener {
            viewModel.getThicks(it.toString(), textViewLength.text.toString(), checkboxTrunkApex.isChecked)
        }

        viewModel.thicks.observe(this) { thickArray ->
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, thickArray.orEmpty())
            adapter.filter.filter(textViewThick.text.toString())
            textViewThick.setAdapter(adapter)
        }

        binding.buttonAdd.setOnClickListener{
            val volume = viewModel.getVolume(textViewLength.text.toString(), textViewThick.text.toString(), checkboxTrunkApex.isChecked)
            println("vera-test $volume")
        }


//        val spinnerWoodType: MaterialAutoCompleteTextView = binding.spinnerWoodType
//        val woodTypes = resources.getStringArray(R.array.woodTypes)
//        val woodAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, woodTypes)
//        spinnerWoodType.setText(woodAdapter.getItem(0))
//        spinnerWoodType.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, woodTypes))
//
//        val spinnerHumidity: MaterialAutoCompleteTextView = binding.spinnerHumidityValue
//        val humidityValues = resources.getStringArray(R.array.humidityValues)
//        val humidityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, humidityValues)
//        spinnerHumidity.setText(humidityAdapter.getItem(0))
//        spinnerHumidity.setAdapter(humidityAdapter)


//        densityViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    companion object {
        const val TAG = "AddDataDialogFragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(ru.vsokolova.volumetric_table.R.style.Theme_CenterDialog)
        }
    }
}