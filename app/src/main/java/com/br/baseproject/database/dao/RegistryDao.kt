package com.br.baseproject.database.dao

import androidx.room.*
import com.br.baseproject.database.model.Registry
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(registry: Registry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(registry: Registry)

    @Delete
    suspend fun delete(registry: Registry)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM user_table")
    fun getAllWords(): List<Registry>

    @Query("SELECT * FROM user_table ORDER BY nome ASC")
    fun getAlphabetizedWords() : Flow<List<Registry>>

}