package com.example.home_task_l30_rxjavav2.ui.showPostsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_task_l30_rxjavav2.data.repository.PostsInfoRepository
import com.example.home_task_l30_rxjavav2.domain.useCases.GetPostsUseCase
import com.example.home_task_l30_rxjavav2.ui.DomainToUiMapper
import com.example.home_task_l30_rxjavav2.ui.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class PostsScreenViewModel @Inject constructor(
    private val allPostsUseCase: GetPostsUseCase,
    postsInfoRepository: PostsInfoRepository,
    private val domainToUiMapper: DomainToUiMapper
) : ViewModel(), Observer {

    init {
        postsInfoRepository.addObserverFun(this)
    }

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>>
        get() = _postsLiveData

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val listPostsUiModel = allPostsUseCase.invoke()?.let { domainToUiMapper.map(it) }
            _postsLiveData.postValue(listPostsUiModel)
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        getPosts()
    }
}