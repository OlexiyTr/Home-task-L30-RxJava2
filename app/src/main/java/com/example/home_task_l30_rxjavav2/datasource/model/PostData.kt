package com.example.home_task_l30_rxjavav2

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.home_task_l30_rxjavav2.datasource.db.Converter
import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom

@TypeConverters(Converter::class)
@Entity
data class PostData(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    val addedFrom: AddedFrom
)

