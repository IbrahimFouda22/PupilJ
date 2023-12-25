package com.pupilJ.jk.fragments.teacher.home.student.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentRoomHomeTeacherBinding
import com.pupilJ.jk.fragments.teacher.home.room.RoomsAdapter
import com.pupilJ.jk.fragments.teacher.home.room.RoomsOnClickListener
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomHomeTeacherFragment : Fragment() {
    private lateinit var binding: FragmentRoomHomeTeacherBinding
    private val viewModel: RoomHomeTeacherViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRoomHomeTeacherBinding.inflate(layoutInflater)

        val adapter = RoomsAdapter(RoomsOnClickListener {
            findNavController().navigate(RoomHomeTeacherFragmentDirections.actionRoomHomeTeacherFragmentToSpecificRoomHomeTeacherFragment(it.name,it.id))
        })

        binding.btnBackHomeTeacherRoom.setOnClickListener {
            back(findNavController())
        }

        binding.recyclerHomeTeacherRooms.adapter = adapter
        viewModel.rooms.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()

        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.GONE
    }

}