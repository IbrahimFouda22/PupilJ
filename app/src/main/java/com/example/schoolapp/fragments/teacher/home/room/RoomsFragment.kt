package com.example.schoolapp.fragments.teacher.home.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentRoomsBinding
import com.example.schoolapp.util.back
import com.example.schoolapp.util.changeStatusBarColor
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    private lateinit var binding:FragmentRoomsBinding
    private val viewModel:RoomsViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRoomsBinding.inflate(layoutInflater)

        val adapter = RoomsAdapter(RoomsOnClickListener {
            findNavController().navigate(RoomsFragmentDirections.actionRoomsFragmentToSpecificRoomFragment(it.name,it.id))
        })

        binding.txtCloseTeacherRoom.setOnClickListener {
            back(findNavController())
        }

        binding.recyclerRooms.adapter = adapter
        viewModel.rooms.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        changeStatusBarColor(requireActivity(),requireActivity().getColor(R.color.canary))

        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.GONE
    }
}