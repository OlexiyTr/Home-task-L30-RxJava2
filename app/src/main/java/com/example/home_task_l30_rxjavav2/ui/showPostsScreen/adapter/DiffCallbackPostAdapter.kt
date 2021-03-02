package com.example.home_task_l30_rxjavav2.ui.showPostsScreen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.home_task_l30_rxjavav2.ui.PostUiModel

class DiffCallbackPostAdapter : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel) = oldItem == newItem
}
