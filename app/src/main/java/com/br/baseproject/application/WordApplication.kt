package com.br.baseproject.application

import android.app.Application
import com.br.baseproject.database.WordRoomDatabase
import com.br.baseproject.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(applicationScope,this) }
    val repository by lazy { WordRepository(database.wordDao()) }

}