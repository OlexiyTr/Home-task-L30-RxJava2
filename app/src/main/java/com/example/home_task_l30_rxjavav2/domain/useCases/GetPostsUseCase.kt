package com.example.home_task_l30_rxjavav2.domain.useCases

import com.example.home_task_l30_rxjavav2.data.repository.PostsInfoRepository
import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostsInfoRepository,
) {
    fun invoke(): List<PostDomainModel>? {
        val postsGroupedByAddedFrom = postRepository.getPostsFromLocalStorage()?.groupBy { it.addedFrom }?.withDefault { emptyList() }
        val listFromServer = postsGroupedByAddedFrom?.getValue(AddedFrom.SERVER)?.sortedBy{ it.id }
        val listFromUser = postsGroupedByAddedFrom?.getValue(AddedFrom.USER)?.sortedByDescending { it.id }
        return listFromUser?.plus(listFromServer!!)
    }
}