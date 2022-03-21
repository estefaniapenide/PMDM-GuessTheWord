package com.example.guesstheword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {
    private var _binding: FragmentTitleBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentTitleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btPlay.setOnClickListener { findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment()) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}