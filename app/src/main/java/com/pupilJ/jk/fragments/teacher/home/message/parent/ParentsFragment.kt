package com.pupilJ.jk.fragments.teacher.home.message.parent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.jk.databinding.FragmentParentsBinding
import com.pupilJ.jk.util.back
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ParentsFragment : Fragment() {

    private lateinit var binding: FragmentParentsBinding

    @Inject
    lateinit var factory: ParentsViewModel.AssistedFactory
    private val navArgs by navArgs<ParentsFragmentArgs>()
    private val viewModel : ParentsViewModel by viewModels {
        ParentsViewModel.parentsViewModel(factory,navArgs.roomId,navArgs.className)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentParentsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        val adapter = ParentsAdapter(ParentsOnClickListener {
            findNavController().navigate(ParentsFragmentDirections.actionParentsFragmentToChatFragment(it.id,it.name))
        })
        binding.recyclerParents.adapter = adapter

        binding.txtBackTeacherParent.setOnClickListener {

            back(findNavController())
        }
        viewModel.parents.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }


        return binding.root
    }

}