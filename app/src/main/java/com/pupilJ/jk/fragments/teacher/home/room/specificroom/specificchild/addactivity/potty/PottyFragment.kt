package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.potty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.pupilJ.domain.models.AdditionalFieldPotty
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentPottyBinding
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PottyFragment : Fragment() {

    private lateinit var binding: FragmentPottyBinding
    private val navArgs by navArgs<PottyFragmentArgs>()
    private val viewModel: PottyViewModel by viewModels({ this })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPottyBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimePotty2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityPottyTeacher.setOnClickListener {
            viewModel.addPotty(
                "potty",
                getAdditionalField(),
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd HH:mm:ss"),
                binding.txtAreaPottyTeacher.text.toString()
            )
        }
        viewModel.addPottyResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        binding.btnBackPottyTeacher.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }

    private fun getAdditionalField(): AdditionalFieldPotty {
        val additionalFieldPotty = AdditionalFieldPotty(
            binding.chipWasteTypePotty.checkedChipId.toString(),
            binding.chipWasteShapePotty.checkedChipId.toString()
        )
        if(binding.chipWasteTypePotty.checkedChipId == R.id.chipDiber_Potty)
            additionalFieldPotty.waste_type = "diber"
        if(binding.chipWasteTypePotty.checkedChipId == R.id.chipPotty_Potty)
            additionalFieldPotty.waste_type = "potty"
        if(binding.chipWasteTypePotty.checkedChipId == R.id.chipAccident_Potty)
            additionalFieldPotty.waste_type = "accident"
        if(binding.chipWasteShapePotty.checkedChipId == R.id.chipWet_Potty)
            additionalFieldPotty.waste_shape = "wet"
        if(binding.chipWasteShapePotty.checkedChipId == R.id.chipDry_Potty)
            additionalFieldPotty.waste_shape = "dry"
        if(binding.chipWasteShapePotty.checkedChipId == R.id.chipBm_Potty)
            additionalFieldPotty.waste_shape = "bm"

        return additionalFieldPotty
    }



}