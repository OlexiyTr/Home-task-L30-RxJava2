package com.example.home_task_l30_rxjavav2.domain.useCases

import com.example.home_task_l30_rxjavav2.PostData
import com.example.home_task_l30_rxjavav2.data.repository.PostsInfoRepository
import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom
import com.example.home_task_l30_rxjavav2.domain.model.NewPostModel
import javax.inject.Inject

class SavePostUseCase @Inject constructor(
    private val repository: PostsInfoRepository
) {
    fun savePost(postForSaving: NewPostModel) {
        repository.putNewPost(mapNewPostToDataPostModel(postForSaving))
    }

    private fun mapNewPostToDataPostModel(postForSaving: NewPostModel) =
        PostData(
            0,
            repository.getNewPostId(),
            postForSaving.title,
            postForSaving.body,
            AddedFrom.USER
        )

}