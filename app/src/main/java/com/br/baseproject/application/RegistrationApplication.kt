package com.br.baseproject.application

import android.app.Application
import com.br.baseproject.database.AppRoomDatabase
import com.br.baseproject.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RegistrationApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppRoomDatabase.getDatabase(applicationScope,this) }
    val repository by lazy { WordRepository(database.registryDao()) }

}