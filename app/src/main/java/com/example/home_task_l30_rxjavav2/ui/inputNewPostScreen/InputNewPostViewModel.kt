package com.example.home_task_l30_rxjavav2.ui.inputNewPostScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_task_l30_rxjavav2.domain.useCases.ValidationUseCase
import com.example.home_task_l30_rxjavav2.domain.model.NewPostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InputNewPostViewModel @Inject constructor(
    private val validationUseCase: ValidationUseCase,
) : ViewModel() {

    val stringErrorLiveData = MutableLiveData<String>()

    fun sendDataToCache(title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val setInputErrors = validationUseCase.validate(NewPostModel(title, body))
            withContext(Dispatchers.Main) {
                stringErrorLiveData.postValue(setInputErrors.toString())
            }
        }
    }
}