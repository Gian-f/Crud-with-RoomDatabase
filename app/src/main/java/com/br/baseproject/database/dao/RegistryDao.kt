package com.br.baseproject.database.dao

import androidx.room.*
import com.br.baseproject.database.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM word_table")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords() : Flow<List<Word>>

}