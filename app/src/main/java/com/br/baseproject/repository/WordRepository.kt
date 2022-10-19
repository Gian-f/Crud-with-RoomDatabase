package com.br.baseproject.repository

import androidx.annotation.WorkerThread
import com.br.baseproject.database.dao.RegistryDao
import com.br.baseproject.database.model.Registry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WordRepository(private val registryDao: RegistryDao) {

    val allWords: Flow<List<Registry>> = registryDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(registry: Registry){
        registryDao.insert(registry)
    }

     fun findAll(){
         CoroutineScope(Dispatchers.IO).launch {
             registryDao.getAllWords()
         }
    }
}