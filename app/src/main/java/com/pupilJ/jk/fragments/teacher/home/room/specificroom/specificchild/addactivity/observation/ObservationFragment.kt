package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.observation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.pupilJ.domain.models.Custom
import com.pupilJ.domain.models.CustomData
import com.pupilJ.jk.databinding.FragmentObservationBinding
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.note.NoteFragmentArgs
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ObservationFragment : Fragment() {
    private val navArgs by navArgs<ObservationFragmentArgs>()
    private val viewModel: ObservationViewModel by viewModels({ this })
    private var listMileStones: List<CustomData>? = null
    private var milestonesSpinnerId: Int? = null
    private var staffOnlyId: Int = 0


    private lateinit var binding : FragmentObservationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentObservationBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeObservation2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        viewModel.mileStonesData.observe(viewLifecycleOwner){
            listMileStones = it.data
            binding.spinnerMileStonesCustom.adapter = createArraySpinner(it)
        }

        binding.spinnerMileStonesCustom.onItemSelectedListener = objectMileStonesSpinner()

        binding.btnBackObservationTeacher.setOnClickListener {
            back(findNavController())
        }

        binding.switchStaffOnlyCustom.setOnCheckedChangeListener(getStaffOnlyId())

        binding.btnAddActivityObservationTeacher.setOnClickListener {
            viewModel.addObservation(
                "observation",
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd HH:mm:ss"),
                binding.txtAreaObservationTeacher.text.toString(),
                milestonesSpinnerId,
                staffOnlyId
            )
        }
        viewModel.addObservationResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }
    private fun createArraySpinner(custom: Custom): ArrayAdapter<String> {
        val l = custom.data.map {
            it.name
        }
        return ArrayAdapter<String>(
            requireContext().applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            l
        )
    }

    private fun objectMileStonesSpinner(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                milestonesSpinnerId = listMileStones!![position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun getStaffOnlyId(): CompoundButton.OnCheckedChangeListener {
        return CompoundButton.OnCheckedChangeListener { _ , isChecked ->
            staffOnlyId = if(isChecked)
                1
            else
                0

        }
    }
}