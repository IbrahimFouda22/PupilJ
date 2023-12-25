package com.pupilJ.jk.fragments.parent.children.childactivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.domain.models.ChildrenActivity
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentChildrenActivitesBinding
import com.pupilJ.jk.util.DatePicker
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.changeStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class ChildrenActivitiesFragment : Fragment(), DatePicker.IDateSetListener {
    private lateinit var binding: FragmentChildrenActivitesBinding
    private val navArgs by navArgs<ChildrenActivitiesFragmentArgs>()
    private lateinit var allList: List<ChildrenActivity>
    private lateinit var filteredList: List<ChildrenActivity>
    private var boolean = true

    @Inject
    lateinit var factory: ChildActivityViewModel.AssistedFactory
    private val viewModel: ChildActivityViewModel by viewModels {
        ChildActivityViewModel.specificChildViewModelFactory(
            factory, navArgs.children.id
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChildrenActivitesBinding.inflate(layoutInflater)

        binding.children = navArgs.children

        //initializeDatePicker()


        binding.txtClose.setOnClickListener {
            back(findNavController())
        }
        //Toast.makeText(context, navArgs.children.name, Toast.LENGTH_SHORT).show()


        val adapter = ChildrenParentAdapter(navArgs.children.name)
        binding.recyclerSpecificChildParent.adapter = adapter
        viewModel.childrenActivity.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            if (boolean) {
                allList = it
                boolean = false
            }
            filteredList = it
            viewModel.setActivityType(getString(R.string.all_activity))
        }
        binding.imgBtnCalender.setOnClickListener {
            showDatePickerDialog()
        }

        binding.imgBtnMessage.setOnClickListener {
            viewModel.navigateToTeacher()
        }
        viewModel.navigateToTeacher.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(
                    ChildrenActivitiesFragmentDirections.actionChildrenActivitiesFragmentToTeachersFragment(
                        navArgs.children.id,
                        navArgs.children.name
                    )
                )
                viewModel.navigateToTeacherDone()
            }
        }

        viewModel.activityType.observe(viewLifecycleOwner) { activityType ->
            binding.txtAllActivityChildrenActivity.text = activityType
            if (activityType == getString(R.string.all_activity)) {
                adapter.submitList(filteredList)
            } else {
                val list = filteredList.filter {
                    it.activityType.replace("_", " ").equals(activityType, ignoreCase = true)
                }
                adapter.submitList(list)
            }
        }

        binding.apply {
            registerForContextMenu(this.btnArrowActivityDownParent)
        }
        binding.btnArrowActivityDownParent.setOnClickListener {
            requireActivity().openContextMenu(it)
        }


        return binding.root
    }


    private fun showDatePickerDialog() {
        val newFragment: DialogFragment = DatePicker(binding.txtDateChildrenActivity)

        (newFragment as DatePicker).setIDateSetListener(this)
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }
//    private fun initializeDatePicker(){
//        val c = Calendar.getInstance()
//        val selectedDate: String =
//            DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(c.time)
//        binding.txtDateChildrenActivity.text = selectedDate
//
//    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_activity, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAllActivity -> viewModel.setActivityType(getString(R.string.all_activity))
            R.id.itemFood -> viewModel.setActivityType(getString(R.string.food))
            R.id.itemNap -> viewModel.setActivityType(getString(R.string.nap))
            R.id.itemNameToFace -> viewModel.setActivityType(getString(R.string.nameToFace))
            R.id.itemObservation -> viewModel.setActivityType(getString(R.string.observation))
            R.id.itemMed -> viewModel.setActivityType(getString(R.string.meds))
            R.id.itemPotty -> viewModel.setActivityType(getString(R.string.potty))
            R.id.itemNote -> viewModel.setActivityType(getString(R.string.note))
            R.id.itemCustom -> viewModel.setActivityType(getString(R.string.custom))
            R.id.itemHealthCheck -> viewModel.setActivityType(getString(R.string.healthCheck))
            R.id.itemIncidents -> viewModel.setActivityType(getString(R.string.incidents))
            R.id.itemAchievement -> viewModel.setActivityType(getString(R.string.achievement))
            R.id.itemPhoto -> viewModel.setActivityType(getString(R.string.photo))
            R.id.itemVideo -> viewModel.setActivityType(getString(R.string.video))
        }
        return super.onContextItemSelected(item)
    }

    @SuppressLint("DefaultLocale")
    override fun processDatePickerResult(year: Int, month: Int, day: Int) {
        val mCalendar = Calendar.getInstance()
        mCalendar[Calendar.YEAR] = year
        mCalendar[Calendar.MONTH] = month
        mCalendar[Calendar.DAY_OF_MONTH] = day
        val selected =
            DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(mCalendar.time)
        val selectedDate = String.format("%d-%02d-%02d", year, month + 1, day)
        binding.txtDateChildrenActivity.text = selected
        filteredList = allList.filter {
            val date = it.activityDate.split(" ")
            date[0] == selectedDate
        }


        viewModel.setChildrenActivity(filteredList)
    }

    override fun onStart() {
        super.onStart()
        changeStatusBarColor(requireActivity(),requireActivity().getColor(R.color.canary))

    }


}