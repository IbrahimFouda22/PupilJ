package com.pupilJ.jk.fragments.joinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pupilJ.jk.databinding.FragmentJoinAsBinding

class JoinAsFragment : Fragment() {
    private lateinit var binding: FragmentJoinAsBinding
    private lateinit var viewModel: JoinAsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentJoinAsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[JoinAsViewModel::class.java]
        binding.imgParent.setOnClickListener {
            viewModel.navigateToParent(true)
        }
        binding.imgTeacher.setOnClickListener {
            viewModel.navigateToTeacher(true)
        }
        viewModel.navigateToParent.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(JoinAsFragmentDirections.actionJoinAsFragmentToSignIn("parent"))
                viewModel.navigateToParentDone()
            }
        }

        viewModel.navigateToTeacher.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(JoinAsFragmentDirections.actionJoinAsFragmentToSignIn("teacher"))
                viewModel.navigateToTeacherDone()
            }
        }

        return binding.root
    }



}