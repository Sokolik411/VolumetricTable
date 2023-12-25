package ru.vsokolova.volumetric_table.ui.volume_table

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.vsokolova.volumetric_table.databinding.DialogVolumeTableDataBinding
import ru.vsokolova.volumetric_table.db.VolumeDatabase


class AddDataDialogFragment : DialogFragment() {

    private var _binding: DialogVolumeTableDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogVolumeTableDataBinding.inflate(layoutInflater)
        val root: View = binding.root


        val isTop = if (binding.checkboxTrunkApex.isChecked) {
            1.toShort()
        } else {
            0.toShort()
        }

        val textViewLength: MaterialAutoCompleteTextView = binding.textViewLength
        val lengthArray = VolumeDatabase.getDatabase(requireContext()).getVolumeDao().getLengthsList(
            "2",
            isTop
        )
//        val test = VolumeDatabase.getDatabase(requireContext()).getVolumeDao().getAll()
//        val woodAdapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, lengthArray)
//        textViewLength.setAdapter(woodAdapter)

//        textViewLength.setOn

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