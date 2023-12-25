package com.pupilJ.jk.fragments.parent.children.mychildrens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pupilJ.jk.R
import com.pupilJ.jk.databinding.FragmentMyChildrensBinding
import com.pupilJ.jk.util.back
import com.pupilJ.jk.util.changeStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyChildrenFragment : Fragment() {
    private lateinit var binding: FragmentMyChildrensBinding
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

        binding.txtBack.setOnClickListener {
            back(findNavController())
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavParent)
        bottomNav.visibility = View.GONE
        changeStatusBarColor(requireActivity(),requireActivity().getColor(R.color.colorPrimary))
    }


}