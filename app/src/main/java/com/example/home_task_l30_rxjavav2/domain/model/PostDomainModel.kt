package com.example.home_task_l30_rxjavav2.domain.model

import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom
import com.example.home_task_l30_rxjavav2.domain.UserState

data class PostDomainModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val userState: UserState,
    val addedFrom: AddedFrom
)