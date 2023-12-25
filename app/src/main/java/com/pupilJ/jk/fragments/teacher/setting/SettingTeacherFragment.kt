package com.pupilJ.jk.fragments.teacher.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pupilJ.jk.databinding.FragmentSettingTeacherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingTeacherFragment : Fragment() {

    private val viewModel by viewModels<SettingTeacherViewModel>({this})
    private lateinit var binding: FragmentSettingTeacherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingTeacherBinding.inflate(layoutInflater)

        binding.btnArrowAboutUsSettingTeacher.setOnClickListener {
            viewModel.navigateToAboutUs()

        }

        binding.btnArrowContactUsSettingTeacher.setOnClickListener {
            viewModel.navigateToContactUs()
        }

//        binding.btnArrowChangeLangSettingTeacher.setOnClickListener {
//            viewModel.navigateToLanguage()
//
//        }

        viewModel.navigateToContactUs.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SettingTeacherFragmentDirections.actionSettingTeacherFragmentToContactUsTeacherFragment())
                viewModel.navigateToContactUsDone()
            }
        }

        viewModel.navigateToAboutUs.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SettingTeacherFragmentDirections.actionSettingTeacherFragmentToAboutUsTeacherFragment())
                viewModel.navigateToAboutUsDone()
            }
        }

        viewModel.navigateToLanguage.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SettingTeacherFragmentDirections.actionSettingTeacherFragmentToLanguageTeacherFragment())
                viewModel.navigateToLanguageDone()
            }
        }

        return binding.root
    }

}