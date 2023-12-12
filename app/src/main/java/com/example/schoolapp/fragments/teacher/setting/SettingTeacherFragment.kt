package com.example.schoolapp.fragments.teacher.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentSettingTeacherBinding

class SettingTeacherFragment : Fragment() {

    private lateinit var binding:FragmentSettingTeacherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingTeacherBinding.inflate(layoutInflater)


        return binding.root
    }

}