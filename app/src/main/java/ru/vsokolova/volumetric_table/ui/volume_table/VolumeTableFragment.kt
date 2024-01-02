package ru.vsokolova.volumetric_table.ui.volume_table

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import ru.vsokolova.volumetric_table.R
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
            println(chipObject.getVolume() + " " + chipObject.getAmount() + " " + chipObject.getResult())
            addChips(chipObject)
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

        viewModel.volumeResult.observe(viewLifecycleOwner) {
            val result = String.format("%.2f", it)
            val textViewResult = binding.textviewResult

            textViewResult
                .animate()
                .alpha(0f)
                .setDuration(600)
                .withEndAction {
                    textViewResult.text =
                        resources.getString(R.string.volume_result_template, result)
                    textViewResult
                        .animate()
                        .alpha(1f)
                        .duration = 600
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addChips(chipObg: ChipObject) {
        val chip = Chip(requireContext())
        val title = chipObg.getVolume() + "*" + chipObg.getAmount() + " шт"
        chip.text = title
        chip.isCloseIconVisible = true
        chip.minWidth = 100
        chip.alpha = 0f
        chip.setOnCloseIconClickListener { view: View? ->
            try {
                viewModel.changeVolumeResult(chipObg.getResult() * (-1))

                chip.animate()
                    .alpha(0f)
                    .setDuration(600)
                    .withEndAction {
                        binding.chipGroup.removeView(view)
                    }

            } catch (e: Exception) {
                println("can't remove chips: $e")
            }
        }
        viewModel.changeVolumeResult(chipObg.getResult())
        binding.chipGroup.addView(chip)
        chip.animate()
            .alpha(1f)
            .duration = 600
    }
}