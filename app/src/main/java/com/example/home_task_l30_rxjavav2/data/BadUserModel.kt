package com.example.home_task_l30_rxjavav2.data

import com.example.home_task_l30_rxjavav2.domain.UserState

data class BadUserModel(
    val userId: Int,
    val userState: UserState
)