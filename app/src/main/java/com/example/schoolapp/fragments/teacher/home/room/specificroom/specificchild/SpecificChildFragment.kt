package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.ChildrenActivity
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentSpecificChildBinding
import com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.dialog.ContactDialog
import com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.dialog.InfoDialog
import com.example.schoolapp.util.back
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class SpecificChildFragment : Fragment() {

    private lateinit var binding:FragmentSpecificChildBinding
    private lateinit var allList:List<ChildrenActivity>
    private val navArgs by navArgs<SpecificChildFragmentArgs>()
    @Inject lateinit var factory:SpecificChildViewModel.AssistedFactory
    private val viewModel:SpecificChildViewModel by viewModels {
        SpecificChildViewModel.specificChildViewModelFactory(factory,navArgs.childrenInfo.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpecificChildBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.className = navArgs.className
        initializeDatePicker()

        binding.txtCloseSpecificChildrenTeacher.setOnClickListener {
            back(findNavController())
        }
        binding.txtProfileSpecificChildrenTeacher.setOnClickListener {
            viewModel.navigateToProfile()
        }
        binding.cardInfoSpecificChildrenTeacher.setOnClickListener{
            InfoDialog(navArgs.childrenInfo).show(requireActivity().supportFragmentManager,"Info")
        }

        binding.cardContactSpecificChildrenTeacher.setOnClickListener{
            ContactDialog(navArgs.childrenInfo).show(requireActivity().supportFragmentManager,"Contact")
        }

        binding.cardAddSpecificChildrenTeacher.setOnClickListener{
            viewModel.navigateToAddActivity()
        }

        viewModel.navigateToAddActivity.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SpecificChildFragmentDirections.actionSpecificChildFragmentToAddActivityFragment(navArgs.childrenInfo))
                viewModel.navigateToAddActivityDone()
            }
        }

        viewModel.navigateToProfile.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SpecificChildFragmentDirections.actionSpecificChildFragmentToSpecificChildProfileFragment(navArgs.className,navArgs.childrenInfo))
                viewModel.navigateToProfileDone()
            }
        }

        val adapter = SpecificChildAdapter(navArgs.childrenInfo.name!!)
        binding.recyclerSpecificChildTeacher.adapter = adapter
        viewModel.childrenActivity.observe(viewLifecycleOwner){
            adapter.submitList(it)
            allList = it
            viewModel.setActivityType(getString(R.string.all_activity))
        }

        viewModel.activityType.observe(viewLifecycleOwner){activityType ->
            binding.txtAllActivityChildrenActivity.text = activityType
            if(activityType == getString(R.string.all_activity)){
                adapter.submitList(allList)
            }else{
                val list = allList.filter {
                    it.activityType.replace("_"," ").equals(activityType, ignoreCase = true)
                }
                adapter.submitList(list)
            }
        }

        binding.apply {
            registerForContextMenu(this.btnArrowActivityDown)
        }
        binding.btnArrowActivityDown.setOnClickListener {
            requireActivity().openContextMenu(it)
        }
        return binding.root
    }

//    override fun onDialogOkClick(dialog: DialogFragment) {
//
//    }

    private fun initializeDatePicker(){
        val c = Calendar.getInstance()
        val selectedDate: String =
            DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(c.time)
        binding.txtDateSpecificChildTeacher.text = selectedDate
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_activity,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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
}