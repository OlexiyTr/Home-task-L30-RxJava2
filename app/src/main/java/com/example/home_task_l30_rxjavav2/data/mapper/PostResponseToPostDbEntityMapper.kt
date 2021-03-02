package com.example.home_task_l30_rxjavav2.data.mapper

import com.example.home_task_l30_rxjavav2.PostData
import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom
import com.example.home_task_l30_rxjavav2.datasource.model.PostResponse
import javax.inject.Inject

class PostResponseToPostDbEntityMapper @Inject constructor() {

    fun map(usersPostsResponseList: List<PostResponse>): List<PostData> {
        return usersPostsResponseList.map {
            PostData(
                it.userId,
                it.id,
                it.title,
                it.body,
                AddedFrom.SERVER
            )
        }
    }
}