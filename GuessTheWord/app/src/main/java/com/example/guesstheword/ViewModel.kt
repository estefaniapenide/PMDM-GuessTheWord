package com.example.guesstheword

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val resources = application.resources

    var word = MutableLiveData("")
    var score = MutableLiveData(0)

    var wordList: MutableList<String>


    init {
        Log.i("ViewModel", "ViewModel created!")
        wordList = resources.getStringArray(R.array.words).toMutableList()
        resetList()
        nextWord()
    }

    private fun resetList() {
        wordList.apply { shuffle() }
    }


    /**
     * Callback called when the ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel destroyed!")
    }

    /** Methods for updating the UI **/
    fun onSkip() {
        score.value?.let { a -> score.value = a - 1}
        nextWord()
    }
    fun onCorrect() {
        score.value?.let { a -> score.value = a + 1}
        nextWord()
    }

    /**
     * Moves to the next word in the list.
     */
    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            word.postValue(wordList.removeAt(0)) // Select and remove a word from the list
        }
    }
}