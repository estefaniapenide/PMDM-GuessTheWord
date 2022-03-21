package com.example.guesstheword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.guesstheword.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private var _binding:FragmentScoreBinding?=null
    private val binding get()=_binding!!

    private val navGraphViewModel : ViewModel by navGraphViewModels<ViewModel>(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding= FragmentScoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navGraphViewModel.score.observe(viewLifecycleOwner){
            binding.tvScore.text=it.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}