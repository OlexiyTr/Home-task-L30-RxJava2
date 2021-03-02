package com.example.home_task_l30_rxjavav2.ui

import androidx.annotation.ColorInt

sealed class PostUiModel {

    data class StandardPostUiModel(
        val postId: Int,
        val userId: String,
        val title: String,
        val body: String,
        val hasWarning: Boolean,
        @ColorInt val backgroundColor: Int,
    ) : PostUiModel()

    data class BannedUserPostUiModel(
        val postId: Int,
        val userId: Int
    ) : PostUiModel()
}