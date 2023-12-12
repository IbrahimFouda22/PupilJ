package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.nap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.AdditionalFieldNap
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentNapBinding
import com.example.schoolapp.util.back
import com.example.schoolapp.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NapFragment : Fragment() {

    private lateinit var binding:FragmentNapBinding
    private val navArgs by navArgs<NapFragmentArgs>()
    private val viewModel: NapViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNapBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeNap2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityNapTeacher.setOnClickListener {
            viewModel.addNap(
                "nap",
                getAdditionalField(),
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd"),
                binding.txtAreaNapTeacher.text.toString()
            )
        }
        viewModel.addNapResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        binding.btnBackNapTeacher.setOnClickListener {
            back(findNavController())
        }
        return binding.root
    }

    private fun getAdditionalField(): AdditionalFieldNap {
        val additionalFieldNap = AdditionalFieldNap(
            binding.chipNapType.checkedChipId.toString(),
        )
        if(binding.chipNapType.checkedChipId == R.id.chipStartNap_nap)
            additionalFieldNap.nap_type = "start_nap"
        if(binding.chipNapType.checkedChipId == R.id.chipEndNap_nap)
            additionalFieldNap.nap_type = "end_nap"
        if(binding.chipNapType.checkedChipId == R.id.chipSleepCheck_nap)
            additionalFieldNap.nap_type = "sleep_check"

        return additionalFieldNap
    }

}