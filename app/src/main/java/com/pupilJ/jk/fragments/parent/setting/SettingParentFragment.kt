package com.pupilJ.jk.fragments.parent.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pupilJ.jk.databinding.FragmentSettingParentBinding
import com.pupilJ.jk.fragments.teacher.setting.SettingTeacherFragmentDirections
import com.pupilJ.jk.fragments.teacher.setting.SettingTeacherViewModel

class SettingParentFragment : Fragment() {
    private lateinit var binding: FragmentSettingParentBinding
    private val viewModel by viewModels<SettingParentViewModel>({this})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingParentBinding.inflate(layoutInflater)

        binding.btnArrowAboutUsSettingParent.setOnClickListener {
            viewModel.navigateToAboutUs()

        }

        binding.btnArrowContactUsSettingParent.setOnClickListener {
            viewModel.navigateToContactUs()
        }

//        binding.btnArrowChangeLangSettingParent.setOnClickListener {
//            viewModel.navigateToLanguage()
//
//        }

        viewModel.navigateToContactUs.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SettingParentFragmentDirections.actionSettingParentFragmentToContactUsParentFragment2())
                viewModel.navigateToContactUsDone()
            }
        }

        viewModel.navigateToAboutUs.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SettingParentFragmentDirections.actionSettingParentFragmentToAboutUsFragment())
                viewModel.navigateToAboutUsDone()
            }
        }

        viewModel.navigateToLanguage.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SettingParentFragmentDirections.actionSettingParentFragmentToLanguageParentFragment())
                viewModel.navigateToLanguageDone()
            }
        }

        return binding.root
    }

}