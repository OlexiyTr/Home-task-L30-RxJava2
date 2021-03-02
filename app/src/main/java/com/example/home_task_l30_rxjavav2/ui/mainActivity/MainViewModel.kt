package com.example.home_task_l30_rxjavav2.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.home_task_l30_rxjavav2.data.repository.PostsInfoRepository
import com.example.home_task_l30_rxjavav2.tools.UpdatingState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: PostsInfoRepository
) : ViewModel() {

    private val _errorLiveData = MutableLiveData<UpdatingState>()
    val errorLiveData: LiveData<UpdatingState> = _errorLiveData

    fun updateRepo() {
        repository.updateLocalStorage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}