package ru.vsokolova.volumetric_table.ui.volume_table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.vsokolova.volumetric_table.Dependencies
import ru.vsokolova.volumetric_table.R
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
        val textViewThick: MaterialAutoCompleteTextView = binding.textViewThick
        val editTextAmount: EditText = binding.editTextAmount

        textViewLength.addTextChangedListener {
            viewModel.getLengths(it.toString(), checkboxTrunkApex.isChecked)
        }

        viewModel.lengths.observe(
            this
        ) { lengthArray ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, lengthArray.orEmpty())
            adapter.filter.filter(textViewLength.text.toString())
            textViewLength.setAdapter(adapter)
        }

        textViewThick.addTextChangedListener {
            viewModel.getThicks(it.toString(), textViewLength.text.toString(), checkboxTrunkApex.isChecked)
        }

        viewModel.thicks.observe(this) { thickArray ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, thickArray.orEmpty())
            adapter.filter.filter(textViewThick.text.toString())
            textViewThick.setAdapter(adapter)
        }

        viewModel.volume.observe(this) { volumeValue ->
            if(volumeValue.isNullOrEmpty()){
                Toast.makeText(requireContext(), resources.getString(R.string.error_incorrect_volume), Toast.LENGTH_SHORT).show()
            } else {
                val amount = editTextAmount.text.toString()
                //todo deleted this
                Toast.makeText(requireContext(), "В БД добавлено $volumeValue", Toast.LENGTH_SHORT).show()
                //save to db
            }
        }

        binding.buttonAdd.setOnClickListener {
            if (editTextAmount.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), resources.getString(R.string.error_empty_amount), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getVolume(textViewLength.text.toString(), textViewThick.text.toString(), checkboxTrunkApex.isChecked)
            }
        }

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