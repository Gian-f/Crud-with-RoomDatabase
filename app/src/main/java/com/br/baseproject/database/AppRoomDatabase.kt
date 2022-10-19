package com.br.baseproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.br.baseproject.database.dao.RegistryDao
import com.br.baseproject.database.model.Registry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Registry::class], version = 1, exportSchema = false)

abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun registryDao(): RegistryDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(scope : CoroutineScope, context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "user_database")
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.registryDao())
                }
            }
        }

        suspend fun populateDatabase(registryDao: RegistryDao) {
            //wordDao.deleteAllWords()
//            var word = Word("Gian")
//            wordDao.insert(word)
//            word = Word("John")
//            wordDao.insert(word)
//            word = Word("João")
//            wordDao.insert(word)
        }
    }

}