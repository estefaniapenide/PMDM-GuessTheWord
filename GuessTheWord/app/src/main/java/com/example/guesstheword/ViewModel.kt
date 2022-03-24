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
    var wordListLiveData = MutableLiveData<MutableList<String>>()

    var contador = MutableLiveData(0)
    var listsize = resources.getStringArray(R.array.words).size


    init {
        Log.i("ViewModel", "ViewModel created!")
        wordList = resources.getStringArray(R.array.words).toMutableList()
        wordListLiveData.setValue(wordList)
        resetList()
        nextWord()
    }

    private fun resetList() {
        wordListLiveData.value?.apply { shuffle() }
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
        if (listsize > contador.value!!) {
            score.value?.let { score.value = it - 1 }
            nextWord()
            contador.value?.let { contador.value = it + 1 }
        }
    }

    fun onCorrect() {
        if (listsize > contador.value!!) {
            score.value?.let { score.value = it + 1 }
            nextWord()
            contador.value?.let { contador.value = it + 1 }
        }
    }

    /**
     * Moves to the next word in the list.
     */
    private fun nextWord() {
        if (!wordListLiveData.value.isNullOrEmpty()) {
       // Log.d("---", wordListLiveData.value?.get(0) ?: "Mal")

        word.postValue(wordListLiveData.value?.removeAt(0))// Select and remove a word from the list
       // Log.d("---", word.toString())
      //  Log.d("---", word.value.toString())

         }
    }
}