package com.example.home_task_l30_rxjavav2.data.mapper

import com.example.home_task_l30_rxjavav2.PostData
import com.example.home_task_l30_rxjavav2.domain.UserState
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import com.example.home_task_l30_rxjavav2.datasource.UserStateConditions
import javax.inject.Inject

class DataToDomainMapper @Inject constructor() {


    fun map(responses: List<PostData>?): List<PostDomainModel>? {
        val blackList = UserStateConditions().getBlackList()
        return responses.let { posts ->
            posts?.map { post ->
                val userState: UserState? = blackList
                    .find { it.userId == post.userId }
                    ?.userState

                PostDomainModel(
                    id = post.id,
                    userId = post.userId,
                    title = post.title,
                    body = post.body,
                    userState = userState ?: UserState.NORMAL,
                    addedFrom = post.addedFrom
                )
            }
        }
    }
}

