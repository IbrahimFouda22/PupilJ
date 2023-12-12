package com.example.schoolapp.fragments.teacher.home.room.specificroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.ChildrenTeacher
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentSpecificRoomBinding
import com.example.schoolapp.util.back
import com.example.schoolapp.util.changeStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SpecificRoomFragment : Fragment() {
    private lateinit var binding:FragmentSpecificRoomBinding
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

            //Log.d("list", list[0].toString())
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

        return binding.root
    }

//    override fun onStart() {
//        super.onStart()
//        changeStatusBarColor(requireActivity(),requireActivity().getColor(R.color.canary))
//    }

}