package com.pupilJ.jk.fragments.teacher.home.reminder.children

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
import com.pupilJ.jk.databinding.FragmentChildrenBinding
import com.pupilJ.jk.databinding.FragmentSpecificRoomBinding
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomAdapter
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomFragmentArgs
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomFragmentDirections
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomOnClickListener
import com.pupilJ.jk.fragments.teacher.home.room.specificroom.SpecificRoomViewModel
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChildrenFragment : Fragment() {

    private lateinit var binding: FragmentChildrenBinding
    @Inject
    lateinit var factory: ChildrenReminderViewModel.AssistedFactory
    private val navArgs by navArgs<ChildrenFragmentArgs>()
    private val viewModel : ChildrenReminderViewModel by viewModels {
        ChildrenReminderViewModel.childrenReminderViewModelFactory(factory,navArgs.roomId,navArgs.className)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChildrenBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        val adapter = ChildrenReminderAdapter(ChildrenReminderOnClickListener {
            findNavController().navigate(ChildrenFragmentDirections.actionChildrenFragmentToAddReminderFragment(it))
        })
        binding.recyclerChildrenReminder.adapter = adapter

        binding.txtBackChildrenReminder.setOnClickListener {

            back(findNavController())
        }
        viewModel.children.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        return binding.root
    }

}