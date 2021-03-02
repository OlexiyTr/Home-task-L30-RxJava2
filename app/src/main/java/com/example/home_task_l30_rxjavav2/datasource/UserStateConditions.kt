package com.example.home_task_l30_rxjavav2.datasource

import com.example.home_task_l30_rxjavav2.domain.UserState
import com.example.home_task_l30_rxjavav2.data.BadUserModel

class UserStateConditions {

    companion object {
        private const val WARNINGS_USER = 3
        private const val WARNINGS_USER_NEXT = 4
        private const val BANNED_USER = 7
    }

    fun getBlackList() : MutableSet<BadUserModel>{
        val badSetModel: MutableSet<BadUserModel> = mutableSetOf()

        badSetModel.add(BadUserModel(WARNINGS_USER, UserState.HAS_WARNING))
        badSetModel.add(BadUserModel(WARNINGS_USER_NEXT, UserState.HAS_WARNING))
        badSetModel.add(BadUserModel(BANNED_USER, UserState.BANNED))

        return badSetModel
    }

}