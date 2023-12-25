package com.pupilJ.jk.fragments.teacher.home.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentRoomsBinding
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomFragmentArgs
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.changeStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    private lateinit var binding: FragmentRoomsBinding
    private val viewModel: RoomsViewModel by viewModels({ this })
    private val navArgs by navArgs<RoomsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRoomsBinding.inflate(layoutInflater)

        val adapter = RoomsAdapter(RoomsOnClickListener {
            if(navArgs.type == "r")
                findNavController().navigate(RoomsFragmentDirections.actionRoomsFragmentToSpecificRoomFragment(it.name,it.id))
            else
                findNavController().navigate(RoomsFragmentDirections.actionRoomsFragmentToParentsFragment(it.name,it.id))
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