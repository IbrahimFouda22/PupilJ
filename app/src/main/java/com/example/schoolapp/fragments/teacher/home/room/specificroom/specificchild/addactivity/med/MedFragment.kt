package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.med

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.schoolapp.databinding.FragmentMedBinding
import com.example.schoolapp.util.back
import com.example.schoolapp.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedFragment : Fragment() {

    private lateinit var binding: FragmentMedBinding
    private val navArgs by navArgs<MedFragmentArgs>()
    private val viewModel: MedViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMedBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeMed2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityMedTeacher.setOnClickListener {
            viewModel.addMed(
                "note",
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd"),
                binding.txtAreaMedTeacher.text.toString()
            )
        }
        viewModel.addMedResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        binding.btnBackMedTeacher.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }

}