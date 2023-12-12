package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.custom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.Custom
import com.example.domain.models.CustomData
import com.example.schoolapp.databinding.FragmentCustomBinding
import com.example.schoolapp.util.back
import com.example.schoolapp.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomFragment : Fragment() {
    private lateinit var binding: FragmentCustomBinding
    private val navArgs by navArgs<CustomFragmentArgs>()
    private val viewModel: CustomViewModel by viewModels({ this })
    private var progressSpinnerId: Int? = null
    private var scaleSpinnerId: Int? = null
    private var categorySpinnerId: Int? = null
    private var listProgress: List<CustomData>? = null
    private var listCategory: List<CustomData>? = null
    private var listScale: List<CustomData>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCustomBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeCustom2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        binding.btnAddActivityCustomTeacher.setOnClickListener {
            viewModel.addCustom(
                "custom",
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd"),
                binding.txtAreaCustomTeacher.text.toString(),
                categorySpinnerId,
                progressSpinnerId,
                scaleSpinnerId
            )
        }
        viewModel.addCustomResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        binding.spinnerProgressCustom.onItemSelectedListener = objectProgressSpinner()
        binding.spinnerCategoryCustom.onItemSelectedListener = objectCategorySpinner()
        binding.spinnerScaleCustom.onItemSelectedListener = objectScaleSpinner()

        viewModel.progressData.observe(viewLifecycleOwner) {
            listProgress = it.data
            binding.spinnerProgressCustom.adapter = createArraySpinner(it)
        }
        viewModel.categoryData.observe(viewLifecycleOwner) {
            listCategory = it.data
            binding.spinnerCategoryCustom.adapter = createArraySpinner(it)
        }
        viewModel.scaleData.observe(viewLifecycleOwner) {
            listScale = it.data
            binding.spinnerScaleCustom.adapter = createArraySpinner(it)
        }

        binding.btnBackCustomTeacher.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }

    private fun createArraySpinner(custom: Custom): ArrayAdapter<String> {
        val l = custom.data.map {
            it.name
        }
        return ArrayAdapter<String>(
            requireContext().applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            l
        )
    }

    private fun objectCategorySpinner(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categorySpinnerId = listCategory!![position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun objectProgressSpinner(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                progressSpinnerId = listProgress!![position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun objectScaleSpinner(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                scaleSpinnerId = listScale!![position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

}