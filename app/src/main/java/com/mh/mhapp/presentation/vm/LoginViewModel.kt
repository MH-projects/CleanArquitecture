package com.mh.mhapp.presentation.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mh.mhapp.State
import com.mh.mhapp.data.model.LoginRequest
import com.mh.mhapp.domain.usecase.InfoUseCase
import com.mh.mhapp.domain.usecase.LoginApiUseCase
import kotlinx.coroutines.launch

// Prepare ViewModel to inject dependencies
/*@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginApiUseCase: LoginApiUseCase
) : ViewModel() {*/

class LoginViewModel : ViewModel() {

    private val loginApiUseCase = LoginApiUseCase()
    private val infoUseCase = InfoUseCase()

    private val _stateLogin: MutableLiveData<State> = MutableLiveData()
    val stateLogin: LiveData<State> get() = _stateLogin

    fun login(ctx: Context, loginRequest: LoginRequest) {
        viewModelScope.launch {
            _stateLogin.postValue(State.Loading)
            try {
                val result = loginApiUseCase(loginRequest)

                println("Result: $result")

                if (result.status) {
                    val infoResult = infoUseCase(ctx, loginRequest.user)

                    println("infoResult: $infoResult")

                    if (infoResult.status) {
                        _stateLogin.postValue(State.Success)
                    } else {
                        _stateLogin.postValue(State.Failure(infoResult.message))
                    }
                } else {
                    _stateLogin.postValue(State.Failure(result.message))
                }
            } catch (e: Exception) {
                _stateLogin.postValue(State.Failure(e.message.toString()))
            }
        }
    }

    /*fun login(loginRequest: LoginRequest) = liveData(Dispatchers.IO) {
        emit(State.Loading)

        try {
            val response = iLogin.login(loginRequest)

            emit(
                if (response.status) {
                    State.Success(true)
                } else {
                    State.Failure(response.message)
                }
            )
        } catch (e: Exception) {
            emit(State.Failure(e.message))
        }
    }*/
}
