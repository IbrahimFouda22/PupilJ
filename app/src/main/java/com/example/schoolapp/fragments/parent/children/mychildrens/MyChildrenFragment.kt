package com.example.schoolapp.fragments.parent.children.mychildrens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentMyChildrensBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyChildrenFragment : Fragment() {
    private lateinit var binding:FragmentMyChildrensBinding
    private val viewModel : ChildrenViewModel by viewModels({ this })
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentMyChildrensBinding.inflate(layoutInflater)

        val adapter = ChildrenAdapter(OnClickListener {
            findNavController().navigate(MyChildrenFragmentDirections.actionMyChildrenFragmentToChildrenActivitiesFragment(it))
        })

        binding.recyclerMyChildren.adapter = adapter
        viewModel.children.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.GONE
    }


}