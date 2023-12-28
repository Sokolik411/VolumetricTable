package ru.vsokolova.volumetric_table.ui.gost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.vsokolova.volumetric_table.R
import ru.vsokolova.volumetric_table.databinding.FragmentGostBinding


class GostFragment : Fragment() {

    private var _binding: FragmentGostBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gostViewModel = ViewModelProvider(this).get(GostViewModel::class.java)

        _binding = FragmentGostBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        binding.listviewGost.adapter = ArrayAdapter(
//                this, R.layout.item_list_view,
//                R.id.textViewCatName, mCatNames
//            )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}