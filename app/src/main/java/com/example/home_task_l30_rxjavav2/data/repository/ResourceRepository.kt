package com.example.home_task_l30_rxjavav2.data.repository

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourceRepository @Inject constructor(private val context: Context) {
    fun getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)
}