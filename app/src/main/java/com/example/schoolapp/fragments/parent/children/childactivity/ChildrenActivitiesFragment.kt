package com.example.schoolapp.fragments.parent.children.childactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.schoolapp.databinding.FragmentChildrenActivitesBinding
import com.example.schoolapp.util.DatePicker
import java.text.DateFormat
import java.util.Calendar


class ChildrenActivitiesFragment : Fragment() {
    private lateinit var binding:FragmentChildrenActivitesBinding
    private val navArgs by navArgs<ChildrenActivitiesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChildrenActivitesBinding.inflate(layoutInflater)

        binding.children = navArgs.children

        initializeDatePicker()
        binding.imgBtnCalender.setOnClickListener {
            showDatePickerDialog()
        }

        //Toast.makeText(context, navArgs.children.name, Toast.LENGTH_SHORT).show()
        return binding.root
    }
    private fun showDatePickerDialog() {
        val newFragment: DialogFragment = DatePicker(binding.txtDateChildrenActivity)
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }
    private fun initializeDatePicker(){
        val c = Calendar.getInstance()
        val selectedDate: String =
            DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(c.time)
        binding.txtDateChildrenActivity.text = selectedDate
    }

}