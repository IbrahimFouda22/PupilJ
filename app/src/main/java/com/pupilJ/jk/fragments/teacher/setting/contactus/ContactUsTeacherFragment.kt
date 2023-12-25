package com.pupilJ.jk.fragments.teacher.setting.contactus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pupilJ.jk.databinding.FragmentContactUsTeacherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactUsTeacherFragment : Fragment() {

    private val viewModel by viewModels<ContactUsTeacherViewModel>({ this })
    private lateinit var binding: FragmentContactUsTeacherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentContactUsTeacherBinding.inflate(layoutInflater)

        binding.btnSendSettingTeacher.setOnClickListener {
            viewModel.contactUS(
                binding.edtNameSettingTeacher.text.toString(),
                binding.edtEmailSettingTeacher.text.toString(),
                binding.edtTitleSettingTeacher.text.toString(),
                binding.edtProblemProfileTeacher.text.toString()
            )
        }

        viewModel.settingResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

}