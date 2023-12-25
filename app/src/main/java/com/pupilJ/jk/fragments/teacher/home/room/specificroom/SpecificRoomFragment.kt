package com.pupilJ.jk.fragments.teacher.home.room.specificroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.jk.databinding.FragmentSpecificRoomBinding
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SpecificRoomFragment : Fragment() {
    private lateinit var binding: FragmentSpecificRoomBinding
    private var list =  arrayListOf<ChildrenTeacher>()
    @Inject lateinit var factory: SpecificRoomViewModel.AssistedFactory
    private val navArgs by navArgs<SpecificRoomFragmentArgs>()
    private val viewModel :SpecificRoomViewModel by viewModels {
        SpecificRoomViewModel.specificRoomViewModelFactory(factory,navArgs.roomId,navArgs.className)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpecificRoomBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        val adapter = SpecificRoomAdapter(SpecificRoomOnClickListener {
            findNavController().navigate(SpecificRoomFragmentDirections.actionSpecificRoomFragmentToSpecificChildFragment(it,navArgs.className))
        })
        binding.recyclerRooms.adapter = adapter

        binding.txtBackTeacherSpecificRoom.setOnClickListener {

            back(findNavController())
        }
        viewModel.children.observe(viewLifecycleOwner){
            list.clear()
            adapter.submitList(it)
            it.toCollection(list)
        }

        binding.constraintAttendanceSpecificRoomTeacher.setOnClickListener {
            viewModel.navigateToAttendance()
        }
        viewModel.navigateToAttendance.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SpecificRoomFragmentDirections.actionSpecificRoomFragmentToAttendanceFragment(navArgs.className,list.toTypedArray(),navArgs.roomId))
                viewModel.navigateToAttendanceDone()
            }
        }

        binding.constraintLayout2.setOnClickListener {
            viewModel.navigateToChat()
        }
        viewModel.navigateToChat.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SpecificRoomFragmentDirections.actionSpecificRoomFragmentToParentsFragment(navArgs.className,navArgs.roomId))
                viewModel.navigateToChatDone()
            }
        }

        return binding.root
    }


}