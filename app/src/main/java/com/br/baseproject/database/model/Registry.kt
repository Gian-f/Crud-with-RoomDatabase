package com.br.baseproject.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Registry(
    @PrimaryKey
    @ColumnInfo(name = "nome")
    val nome: String,

    @ColumnInfo(name = "telefone")
    val telefone: String? = null,

    @ColumnInfo(name = "data")
    val dataCriacao: String? = null,
    )
