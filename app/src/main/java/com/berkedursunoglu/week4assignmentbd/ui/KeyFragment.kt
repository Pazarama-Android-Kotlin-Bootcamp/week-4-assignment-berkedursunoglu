package com.berkedursunoglu.week4assignmentbd.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.berkedursunoglu.week4assignmentbd.R
import com.berkedursunoglu.week4assignmentbd.Utils.SharedPref
import com.berkedursunoglu.week4assignmentbd.databinding.FragmentKeyBinding


class KeyFragment : Fragment() {

    private lateinit var binding:FragmentKeyBinding
    private lateinit var sharedPref:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        sharedPref = SharedPref(requireContext())
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_key,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.btnEnterKey.setOnClickListener {view->
                val key = binding.etEnterText.text.toString()
                key.let {safeArgs->
                    //Shared set key value
                    sharedPref.setKey(safeArgs)
                    keyToDetailFragment(view)
                }

            }

    }


    private fun keyToDetailFragment(view:View){
        val action = KeyFragmentDirections.actionKeyFragmentToDetailFragment()
        view.findNavController().navigate(action)
    }

}