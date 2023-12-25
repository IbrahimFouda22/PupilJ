package com.pupilJ.jk.fragments.teacher.home.reminder.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pupilJ.jk.databinding.FragmentRoomReminderBinding
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomReminderFragment : Fragment() {
    private lateinit var binding: FragmentRoomReminderBinding
    private val viewModel: RoomsReminderViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentRoomReminderBinding.inflate(layoutInflater)

        val adapter = RoomsReminderAdapter(RoomsReminderOnClickListener {
            findNavController().navigate(RoomReminderFragmentDirections.actionRoomReminderFragmentToChildrenFragment(it.name,it.id))
        })

        binding.txtCloseRoomReminder.setOnClickListener {
            back(findNavController())
        }

        binding.recyclerRoomsReminder.adapter = adapter
        viewModel.rooms.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        return binding.root
    }

}