package com.example.home_task_l30_rxjavav2.ui.showPostsScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.home_task_l30_rxjavav2.R
import com.example.home_task_l30_rxjavav2.ui.PostUiModel
import com.example.home_task_l30_rxjavav2.ui.showPostsScreen.PostType
import com.example.home_task_l30_rxjavav2.ui.showPostsScreen.holder.BannedPostHolder
import com.example.home_task_l30_rxjavav2.ui.showPostsScreen.holder.StandardPostHolder

class PostsAdapter : ListAdapter<PostUiModel, RecyclerView.ViewHolder>(
    DiffCallbackPostAdapter()
) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PostUiModel.StandardPostUiModel -> PostType.STANDARD
            is PostUiModel.BannedUserPostUiModel -> PostType.BANNED
        }.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewTypeEnum = PostType.values()[viewType]
        val view = if (viewTypeEnum == PostType.STANDARD) {
            LayoutInflater.from(parent.context).inflate(R.layout.standart_post_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.banned_post_item, parent, false)
        }

        return if (viewTypeEnum == PostType.STANDARD) {
            StandardPostHolder(view)
        } else {
            BannedPostHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StandardPostHolder -> holder.onBind(getItem(position) as PostUiModel.StandardPostUiModel)
            is BannedPostHolder -> holder.onBind(getItem(position) as PostUiModel.BannedUserPostUiModel)
        }
    }
}