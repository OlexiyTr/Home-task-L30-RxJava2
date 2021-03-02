package com.example.home_task_l30_rxjavav2.ui.showPostsScreen.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.home_task_l30_rxjavav2.R
import com.example.home_task_l30_rxjavav2.databinding.BannedPostItemBinding
import com.example.home_task_l30_rxjavav2.ui.PostUiModel

class BannedPostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = BannedPostItemBinding.bind(itemView)
    fun onBind(post: PostUiModel.BannedUserPostUiModel) {
        val stringWithUserId: String =
            String.format(itemView.resources.getString(R.string.ban), post.userId)
        binding.apply { tvBannedText.text = stringWithUserId }
    }
}