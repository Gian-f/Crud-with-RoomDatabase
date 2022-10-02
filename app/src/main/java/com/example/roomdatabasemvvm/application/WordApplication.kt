package com.example.roomdatabasemvvm.application

import android.app.Application
import com.example.roomdatabasemvvm.database.WordRoomDatabase
import com.example.roomdatabasemvvm.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(applicationScope,this) }
    val repository by lazy { WordRepository(database.wordDao()) }

}