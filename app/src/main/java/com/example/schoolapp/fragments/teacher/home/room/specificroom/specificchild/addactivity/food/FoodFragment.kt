package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.food

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.AdditionalFieldFood
import com.example.domain.models.MealType
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentFoodBinding
import com.example.schoolapp.util.back
import com.example.schoolapp.util.getTimeNow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodFragment : Fragment() {
    private lateinit var binding: FragmentFoodBinding
    private var listMealItems: List<MealType>? = null
    private var listMealItemsToSend = arrayListOf<Int>()
    private var listMealType: List<MealType>? = null
    private var mealTypeId: Int? = null
    private val navArgs by navArgs<FoodFragmentArgs>()
    private val viewModel: FoodViewModel by viewModels({ this })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(layoutInflater)
        binding.children = navArgs.childrenInfo
        binding.txtTimeFood2Teacher.text = getTimeNow("EEE,MMM d,'at' HH:mm aaa")

        viewModel.mealType.observe(viewLifecycleOwner) {
            binding.spinnerTypeFoodTeacher.adapter = createArraySpinner(it)
            listMealType = it
        }

        binding.spinnerTypeFoodTeacher.onItemSelectedListener = objectSpinner()

        viewModel.mealItems.observe(viewLifecycleOwner) {
            listMealItems = it
        }

        binding.apply {
            registerForContextMenu(this.imgBtnAddItemFood)
        }

        binding.imgBtnAddItemFood.setOnClickListener {
            requireActivity().openContextMenu(it)
        }

        viewModel.mealItemSelected.observe(viewLifecycleOwner) { name ->
            if (binding.txtMealItemFood2Teacher.text.isNullOrEmpty())
                binding.txtMealItemFood2Teacher.text = name
            else
                "${this.binding.txtMealItemFood2Teacher.text} + $name".also {
                    binding.txtMealItemFood2Teacher.text = it
                }
        }

        binding.btnAddActivityFoodTeacher.setOnClickListener {
            viewModel.addFood(
                "food",
                getAdditionalField(),
                listMealItemsToSend,
                mealTypeId!!,
                navArgs.childrenInfo.id,
                getTimeNow("yyyy-MM-dd"),
                binding.txtAreaFood.text.toString()
            )
        }
        viewModel.addFoodResponse.observe(viewLifecycleOwner){
            Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
        }

        binding.btnBackFoodTeacher.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_meal_item, menu)
        if (listMealItems != null) {
            for (obj in listMealItems!!) {
                menu.add(0, obj.id, Menu.NONE, obj.nameEn)
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (listMealItems != null) {
            val item = listMealItems!!.find {
                it.id == item.itemId
            }
            if (item != null) {
                viewModel.setMealItemSelect(item.name)
                listMealItemsToSend.add(item.id)
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun createArraySpinner(listMealItems: List<MealType>): ArrayAdapter<String> {
        val l = listMealItems.map {
            it.name
        }
        return ArrayAdapter<String>(
            requireContext().applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            l
        )
    }

    private fun objectSpinner(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (listMealType != null) {
                    viewModel.getMealItems(listMealType!![position].id)
                    mealTypeId = listMealType!![position].id
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun getAdditionalField():AdditionalFieldFood{
        val additionalFieldFood = AdditionalFieldFood(
            binding.chipFoodType.checkedChipId.toString(),
            binding.chipEatType.checkedChipId.toString()
        )
        if(binding.chipFoodType.checkedChipId == R.id.chipFood_FoodType)
            additionalFieldFood.food_type = "food"
        if(binding.chipFoodType.checkedChipId == R.id.chipBottle_FoodType)
            additionalFieldFood.food_type = "bottle"
        if(binding.chipEatType.checkedChipId == R.id.chipAll_EatType)
            additionalFieldFood.eat_type = "all"
        if(binding.chipEatType.checkedChipId == R.id.chipMost_EatType)
            additionalFieldFood.eat_type = "most"
        if(binding.chipEatType.checkedChipId == R.id.chipNone_EatType)
            additionalFieldFood.eat_type = "none"
        if(binding.chipEatType.checkedChipId == R.id.chipSome_EatType)
            additionalFieldFood.eat_type = "some"
        return additionalFieldFood
    }


}