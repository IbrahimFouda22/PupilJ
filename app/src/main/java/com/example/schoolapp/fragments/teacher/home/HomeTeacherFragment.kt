package com.example.schoolapp.fragments.teacher.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentHomeTeacherBinding
import com.example.schoolapp.util.changeStatusBarColor
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeTeacherFragment : Fragment() {

    private lateinit var binding:FragmentHomeTeacherBinding
    private val viewModel: HomeTeacherViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeTeacherBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this)[HomeTeacherViewModel::class.java]
        binding.imgRoomHomeTeacher.setOnClickListener {
            viewModel.navigateToRooms(true)
        }

        viewModel.navigateToRooms.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(HomeTeacherFragmentDirections.actionHomeTeacherFragmentToRoomsFragment())
                viewModel.navigateToRoomsDone()
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