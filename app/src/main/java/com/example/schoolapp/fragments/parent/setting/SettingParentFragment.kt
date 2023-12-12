package com.example.schoolapp.fragments.parent.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentHomeParentBinding
import com.example.schoolapp.databinding.FragmentProfileParentBinding
import com.example.schoolapp.databinding.FragmentSettingParentBinding

class SettingParentFragment : Fragment() {
    private lateinit var binding: FragmentSettingParentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingParentBinding.inflate(layoutInflater)

        return binding.root
    }

}