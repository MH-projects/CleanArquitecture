package com.mh.mhapp.presentation.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mh.mhapp.data.model.UserInfo
import com.mh.mhapp.domain.usecase.InfoUseCase
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _infoUser = MutableLiveData<UserInfo>()
    val infoUser: LiveData<UserInfo> get() = _infoUser

    private val infoUseCase = InfoUseCase()

    fun getInfo(ctx: Context, user: String) {
        viewModelScope.launch {
            val result = infoUseCase.getLocalInfoUser(ctx, user)
            _infoUser.postValue(result)
        }
    }
}
