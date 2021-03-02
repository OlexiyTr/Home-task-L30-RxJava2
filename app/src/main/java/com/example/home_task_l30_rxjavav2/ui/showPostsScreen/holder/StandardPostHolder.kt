package com.example.home_task_l30_rxjavav2.ui.showPostsScreen.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.home_task_l30_rxjavav2.databinding.StandartPostItemBinding
import com.example.home_task_l30_rxjavav2.ui.PostUiModel

class StandardPostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = StandartPostItemBinding.bind(itemView)

    fun onBind(post: PostUiModel.StandardPostUiModel) {
        binding.apply {
            tvUserId.text = post.userId
            tvTitle.text = post.title
            tvBody.text = post.body
            standardPostItemContainer.setBackgroundColor(post.backgroundColor)
            if (post.hasWarning) {
                tvWarningText.visibility = View.VISIBLE
            } else {
                tvWarningText.visibility = View.GONE
            }
        }
    }
}