package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.healthcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.pupilJ.jk.databinding.FragmentHealthCheckBinding
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HealthCheckFragment : Fragment() {

    private lateinit var binding: FragmentHealthCheckBinding
    private val navArgs by navArgs<HealthCheckFragmentArgs>()
    private val viewModel: HealthCheckViewModel by viewModels({ this })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHealthCheckBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeHealthCheck2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityHealthCheckTeacher.setOnClickListener {
            viewModel.addHealthCheck(
                "health_check",
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd HH:mm:ss"),
                binding.txtAreaHealthCheckTeacher.text.toString(),
                binding.txtTempHealthCheck2Teacher.text.toString()
            )
        }
        viewModel.addHealthCheckResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }
        binding.btnBackHealthCheckTeacher.setOnClickListener {
            back(findNavController())
        }
        return binding.root
    }

}