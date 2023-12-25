package com.pupilJ.jk.fragments.parent.setting.contactus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentContactUsParentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactUsParentFragment : Fragment() {
    private val viewModel by viewModels<ContactUsParentViewModel>({ this })
    private lateinit var binding: FragmentContactUsParentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentContactUsParentBinding.inflate(layoutInflater)

        binding.btnSendSettingParent.setOnClickListener {
            viewModel.contactUS(
                binding.edtNameSettingParent.text.toString(),
                binding.edtEmailSettingParent.text.toString(),
                binding.edtTitleSettingParent.text.toString(),
                binding.edtProblemProfileParent.text.toString()
            )
        }

        viewModel.settingResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.INVISIBLE
    }

}