package com.example.home_task_l30_rxjavav2.datasource.db

import androidx.room.TypeConverter
import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom

class Converter {

    @TypeConverter
    fun intToAddedFrom(value: Int) = enumValues<AddedFrom>()[value]

    @TypeConverter
    fun addedFromToInt(value: AddedFrom) = value.ordinal
}