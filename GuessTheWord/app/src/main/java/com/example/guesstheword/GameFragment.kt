package com.example.guesstheword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.guesstheword.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private var _binding:FragmentGameBinding?=null
    private val binding get()=_binding!!

    private val navGraphViewModel : ViewModel by navGraphViewModels<ViewModel>(R.id.navigation) {
        defaultViewModelProviderFactory
    }

    lateinit var lista:MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        updateWordText()
        updateScoreText()

        binding.btGotIt.setOnClickListener { onCorrect() }
        binding.btSkip.setOnClickListener { onSkip() }
        binding.btEndGame.setOnClickListener { onEndGame() }
    }

    private fun onCorrect(){
        juegoTerminado()
        navGraphViewModel.onCorrect()
    }

    private fun onSkip(){
        juegoTerminado()
        navGraphViewModel.onSkip()
    }

    private fun onEndGame(){
        Toast.makeText(activity,"Game has just finished",Toast.LENGTH_SHORT).show()
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment())
    }

    private fun updateWordText(){
        navGraphViewModel.word.observe(viewLifecycleOwner){
            binding.tvWord.text=it
        }
    }

    private fun updateScoreText(){
        navGraphViewModel.score.observe(viewLifecycleOwner){
            binding.tvScoreGame.text=it.toString()
        }
    }


    private fun juegoTerminado(){
        navGraphViewModel.wordListLiveData.observe(viewLifecycleOwner){
            lista=it
        }
        if(lista.isNullOrEmpty()){
            onEndGame()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}