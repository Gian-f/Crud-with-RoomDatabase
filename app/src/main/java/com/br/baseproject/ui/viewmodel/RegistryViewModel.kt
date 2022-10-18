package com.br.baseproject.ui.viewmodel

import androidx.lifecycle.*
import com.br.baseproject.database.model.Registry
import com.br.baseproject.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository): ViewModel() {

    val allWords: LiveData<List<Registry>> = repository.allWords.asLiveData()

    fun insert(registry: Registry)=viewModelScope.launch {
        repository.insert(registry)
    }

    class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(repository) as T
            }
            throw IllegalArgumentException("ViewModel desconhecida")
        }
    }
}
