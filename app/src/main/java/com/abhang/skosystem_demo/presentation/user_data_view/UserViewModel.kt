package com.abhang.skosystem_demo.presentation.user_data_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhang.skosystem_demo.domain.models.UserData
import com.abhang.skosystem_demo.domain.usecase.UserDataUseCase
import com.abhang.skosystem_demo.utils.ResponseState
import com.abhang.skosystem_demo.utils.StateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userDataUseCase: UserDataUseCase
): ViewModel() {
    private var currPage = 1
    private val _userValue = MutableStateFlow(StateHandler<List<UserData>>())
    var userValue : StateFlow<StateHandler<List<UserData>>> = _userValue

    fun getUSerData() = viewModelScope.launch(Dispatchers.IO) {
        if(currPage == 5){
            return@launch
        }
        userDataUseCase(currPage).collect{
            when(it){
                is ResponseState.Loading -> _userValue.value = StateHandler(isLoading = true)
                is ResponseState.Success -> {
                    currPage++
                    _userValue.value = StateHandler(isLoading = false, data = it.data)
                }
                is ResponseState.Error -> _userValue.value = StateHandler(isLoading = false, error = it.message.toString())
            }
        }
    }
}