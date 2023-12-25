package com.pupilJ.jk.fragments.parent.children.chat.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.jk.databinding.FragmentTeachersBinding
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeachersFragment : Fragment() {

    private lateinit var binding: FragmentTeachersBinding

    @Inject
    lateinit var factory: TeachersViewModel.AssistedFactory
    private val navArgs by navArgs<TeachersFragmentArgs>()
    private val viewModel : TeachersViewModel by viewModels {
        TeachersViewModel.teachersViewModel(factory,navArgs.childId,navArgs.childName)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeachersBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        val adapter = TeachersAdapter(TeachersOnClickListener {
            findNavController().navigate(TeachersFragmentDirections.actionTeachersFragmentToChatParentFragment(it.id,it.name))
        })
        binding.recyclerTeachers.adapter = adapter

        binding.txtBackTeacherTeachers.setOnClickListener {

            back(findNavController())
        }
        viewModel.teachers.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }


        return binding.root
    }
}