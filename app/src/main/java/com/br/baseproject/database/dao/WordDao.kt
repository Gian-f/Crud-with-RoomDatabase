package com.br.baseproject.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.baseproject.database.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM word_table")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords() : Flow<List<Word>>

}