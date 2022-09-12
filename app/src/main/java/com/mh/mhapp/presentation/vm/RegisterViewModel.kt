package com.mh.mhapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mh.mhapp.State
import com.mh.mhapp.data.model.RegisterRequest
import com.mh.mhapp.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val registerUseCase = RegisterUseCase()

    private val _stateRegister: MutableLiveData<State> = MutableLiveData()
    val stateRegister: LiveData<State> get() = _stateRegister

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _stateRegister.postValue(State.Loading)
            val result = registerUseCase(registerRequest)
            if (result.status) {
                _stateRegister.postValue(State.Success)
            } else {
                _stateRegister.postValue(State.Failure(result.message))
            }
        }
    }
}
