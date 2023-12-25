package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.incidents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.pupilJ.jk.databinding.FragmentIncidentsBinding
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentsFragment : Fragment() {
    private lateinit var binding: FragmentIncidentsBinding
    private val navArgs by navArgs<IncidentsFragmentArgs>()
    private val viewModel: IncidentViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIncidentsBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeIncidents2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityIncidentsTeacher.setOnClickListener {
            viewModel.addIncident(
                "incident",
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd HH:mm:ss"),
                binding.txtAreaIncidentsTeacher.text.toString()
            )
        }
        viewModel.addIncidentResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        binding.btnBackIncidentsTeacher.setOnClickListener {
            back(findNavController())
        }
        return binding.root
    }

}