package com.example.home_task_l30_rxjavav2.ui.showPostsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home_task_l30_rxjavav2.App
import com.example.home_task_l30_rxjavav2.R
import com.example.home_task_l30_rxjavav2.databinding.PostsFragmentBinding
import com.example.home_task_l30_rxjavav2.ui.showPostsScreen.adapter.PostsAdapter
import com.example.home_task_l30_rxjavav2.ui.PostUiModel
import com.example.home_task_l30_rxjavav2.ui.inputNewPostScreen.InputNewPostFragment
import javax.inject.Inject

class PostsScreenFragment : Fragment() {

    @Inject
    lateinit var screenViewModel: PostsScreenViewModel

    private lateinit var binding: PostsFragmentBinding
    private val postRecycleViewAdapter = PostsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDi()
        observeLiveData()
        setupListeners()
        setupRecyclerView()
        setupData()
    }

    private fun setupDi() {
        val app = requireActivity().application as App
        app.getComponent().inject(this)
    }

    private fun observeLiveData() {
        screenViewModel.postsLiveData.observe(viewLifecycleOwner, {
            updatePostsRecyclerView(it)
        })
    }

    private fun setupRecyclerView() {
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(
                this@PostsScreenFragment.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = postRecycleViewAdapter
        }
    }

    private fun updatePostsRecyclerView(items: List<PostUiModel>) {
        postRecycleViewAdapter.submitList(items)
    }

    private fun setupListeners() {
        binding.btnCreateNewPost.setOnClickListener {
            startAddNewPostFragment()
        }
    }

    private fun setupData() {
        screenViewModel.getPosts()
    }

    private fun startAddNewPostFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment, InputNewPostFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = PostsScreenFragment()
    }

}