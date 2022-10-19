package com.br.baseproject.ui.viewmodel

import androidx.lifecycle.*
import com.br.baseproject.database.model.Registry
import com.br.baseproject.repository.WordRepository
import kotlinx.coroutines.launch

class RegistryViewModel(private val repository: WordRepository): ViewModel() {

    val allWords: LiveData<List<Registry>> = repository.allWords.asLiveData()

    fun insert(registry: Registry) = viewModelScope.launch {
        repository.insert(registry)
    }

    fun findAll() = viewModelScope.launch {
        repository.findAll()
    }

    class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegistryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RegistryViewModel(repository) as T
            }
            throw IllegalArgumentException("ViewModel desconhecida")
        }
    }
}
