package com.example.home_task_l30_rxjavav2.ui

import com.example.home_task_l30_rxjavav2.R
import com.example.home_task_l30_rxjavav2.data.repository.ResourceRepository
import com.example.home_task_l30_rxjavav2.domain.UserState
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import javax.inject.Inject


class DomainToUiMapper @Inject constructor(private val resourceRepository: ResourceRepository) {
    fun map(domainListModel: List<PostDomainModel>): List<PostUiModel> {
        return domainListModel.let(this::getPostUiModels)
    }

    private fun getPostUiModels(postDomainModel: List<PostDomainModel>): List<PostUiModel> {
        return postDomainModel.map {
            when (it.userState) {
                UserState.NORMAL -> {
                    getStandardPostUiModel(it)
                }
                UserState.HAS_WARNING -> {
                    getStandardPostUiModel(it)
                }
                UserState.BANNED -> {
                    getUserPostUiModelBanned(it)
                }
            }
        }
    }

    private fun getStandardPostUiModel(postDomainModel: PostDomainModel): PostUiModel.StandardPostUiModel {
        val (backgroundColor, hasWarning) = when (postDomainModel.userState) {
            UserState.HAS_WARNING -> Pair(
                resourceRepository.getColor(R.color.red),
                true
            )
            else -> Pair(
                resourceRepository.getColor(R.color.white),
                false
            )
        }

        return PostUiModel.StandardPostUiModel(
            postId = postDomainModel.id,
            userId = postDomainModel.userId.toString(),
            title = postDomainModel.title,
            body = postDomainModel.body,
            hasWarning = hasWarning,
            backgroundColor = backgroundColor
        )
    }

    private fun getUserPostUiModelBanned(postDomainModel: PostDomainModel): PostUiModel.BannedUserPostUiModel {
        return PostUiModel.BannedUserPostUiModel(
            postId = postDomainModel.id,
            userId = postDomainModel.userId
        )
    }

}