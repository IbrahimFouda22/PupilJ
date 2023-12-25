package com.pupilJ.jk.fragments.teacher.home.reminder.addreminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentAddReminderBinding
import com.pupilJ.jk.util.DatePicker
import com.pupilJ.jk.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddReminderFragment : Fragment(),DatePicker.IDateSetListener {
    private val navArgs by navArgs<AddReminderFragmentArgs>()
    private val viewModel: AddReminderViewModel by viewModels({ this })
    var activityType : String? = null
    var activityDate = getTimeNow("yyyy-MM-dd")
    private var repeatUntil: Int = 0
    private lateinit var binding:FragmentAddReminderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddReminderBinding.inflate(layoutInflater)
        binding.children = navArgs.children
        binding.txtNextReminder2.text = activityDate
        binding.switchRepeat.setOnCheckedChangeListener(getRepeat())
        binding.btnCreateReminder.setOnClickListener {
            viewModel.addReminder(
                getType(),navArgs.children.id, activityDate,repeatUntil
            )
        }
        viewModel.addReminderResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        binding.imgEdit.setOnClickListener {
            showDatePickerDialog()
        }
        return binding.root
    }

    private fun getRepeat(): CompoundButton.OnCheckedChangeListener {
        return CompoundButton.OnCheckedChangeListener { _, isChecked ->
            repeatUntil = if(isChecked)
                1
            else
                0

        }
    }

    private fun getType(): String? {
        if(binding.chipAddReminder.checkedChipId == R.id.chipFoodAddReminder)
            activityType = requireContext().getString(R.string.foodSmall)

        if(binding.chipAddReminder.checkedChipId == R.id.chipDiberAddReminder)
            activityType = requireContext().getString(R.string.diberSmall)

        if(binding.chipAddReminder.checkedChipId == R.id.chipMedAddReminder)
            activityType = requireContext().getString(R.string.medsSmall)

        if(binding.chipAddReminder.checkedChipId == R.id.chipNotesAddReminder)
            activityType = requireContext().getString(R.string.noteSmall)

        if(binding.chipAddReminder.checkedChipId == R.id.chipNapAddReminder)
            activityType = requireContext().getString(R.string.napSmall)

        if(binding.chipAddReminder.checkedChipId == R.id.chipToiletAddReminder)
            activityType = requireContext().getString(R.string.toiletSmall)

        return activityType
    }

    private fun showDatePickerDialog() {
        val newFragment: DialogFragment = DatePicker(binding.txtNextReminder2)

        (newFragment as DatePicker).setIDateSetListener(this)
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    override fun processDatePickerResult(year: Int, month: Int, day: Int) {
        activityDate = String.format("%d-%02d-%02d", year, month+1, day)
    }
}