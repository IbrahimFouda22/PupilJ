package com.pupilJ.jk.fragments.teacher.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentHomeTeacherBinding
import com.pupilJ.jk.util.changeStatusBarColor

class HomeTeacherFragment : Fragment() {

    private lateinit var binding: FragmentHomeTeacherBinding
    private lateinit var type: String
    private val viewModel: HomeTeacherViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeTeacherBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this)[HomeTeacherViewModel::class.java]
        binding.imgRoomHomeTeacher.setOnClickListener {
            type = "r"
            viewModel.navigateToRooms()
        }

        binding.imgMessageHomeTeacher.setOnClickListener {
            type = "m"
            viewModel.navigateToRooms()
        }

        binding.imgStudentHomeTeacher.setOnClickListener {
            viewModel.navigateToStudents()
        }

        binding.imgReminderHomeTeacher.setOnClickListener {
            viewModel.navigateToReminder()
        }

        viewModel.navigateToRooms.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(HomeTeacherFragmentDirections.actionHomeTeacherFragmentToRoomsFragment(type))
                viewModel.navigateToRoomsDone()
            }
        }
        viewModel.navigateToStudents.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(HomeTeacherFragmentDirections.actionHomeTeacherFragmentToRoomHomeTeacherFragment())
                viewModel.navigateToStudentsDone()
            }
        }

        viewModel.navigateToReminder.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(HomeTeacherFragmentDirections.actionHomeTeacherFragmentToReminderFragment())
                viewModel.navigateToReminderDone()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        changeStatusBarColor(requireActivity(),requireActivity().getColor(R.color.colorPrimary))
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.VISIBLE
    }

}