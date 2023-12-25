package com.pupilJ.jk.fragments.teacher.home.student.specificroomteacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentSpecificRoomBinding
import com.pupilJ.jk.databinding.FragmentSpecificRoomHomeTeacherBinding
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomAdapter
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomFragmentArgs
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomFragmentDirections
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomOnClickListener
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomViewModel
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SpecificRoomHomeTeacherFragment : Fragment() {
    private lateinit var binding: FragmentSpecificRoomHomeTeacherBinding
    @Inject
    lateinit var factory: SpecificRoomHomeTeacherViewModel.AssistedFactory
    private val navArgs by navArgs<SpecificRoomFragmentArgs>()
    private val viewModel : SpecificRoomHomeTeacherViewModel by viewModels {
        SpecificRoomHomeTeacherViewModel.specificRoomViewModelFactory(factory,navArgs.roomId,navArgs.className)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpecificRoomHomeTeacherBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        val adapter = SpecificRoomAdapter(SpecificRoomOnClickListener {
            findNavController().navigate(SpecificRoomHomeTeacherFragmentDirections.actionSpecificRoomHomeTeacherFragmentToSpecificChildHomeTeacherFragment(it,navArgs.className))
        })
        binding.recyclerSpecificChildHomeTeacher.adapter = adapter

        binding.txtBackTeacherSpecificRoomHomeTeacher.setOnClickListener {

            back(findNavController())
        }
        viewModel.children.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        return binding.root    }

}