package ru.vsokolova.volumetric_table.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.vsokolova.volumetric_table.R
import ru.vsokolova.volumetric_table.databinding.FragmentDensityBinding
import ru.vsokolova.volumetric_table.di.AppWithProvider
import ru.vsokolova.volumetric_table.di.ViewModelFactory
import ru.vsokolova.volumetric_table.di.DensityFragmentComponent
import ru.vsokolova.volumetric_table.utils.alphaAnimate
import javax.inject.Inject

class DensityFragment : Fragment() {

    private var _binding: FragmentDensityBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<DensityViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityFragmentComponent
            .create((requireActivity().application as AppWithProvider).getAppProvider())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDensityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerWoodType: MaterialAutoCompleteTextView = binding.spinnerWoodType
        val spinnerHumidity: MaterialAutoCompleteTextView = binding.spinnerHumidityValue
        val textViewResult: TextView = binding.textViewResult

        //adapter created with delay before recreated
        binding.root.post {
            val woodAdapter = ArrayAdapter.createFromResource(
                requireContext(), R.array.woodTypes, android.R.layout.simple_list_item_1
            )
            spinnerWoodType.setAdapter(woodAdapter)
            spinnerWoodType.onItemClickListener = viewModel.onWoodTypeClickListener

            val humidityAdapter = ArrayAdapter.createFromResource(
                requireContext(), R.array.humidityValues, android.R.layout.simple_list_item_1
            )
            spinnerHumidity.setAdapter(humidityAdapter)
            spinnerHumidity.onItemClickListener = viewModel.onHumidityClickListener
        }

        viewModel.density.observe(viewLifecycleOwner) {
            textViewResult.alphaAnimate(0f) {
                textViewResult.text =
                    resources.getString(R.string.density_result_template, it)
                textViewResult.alphaAnimate(1f) {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}