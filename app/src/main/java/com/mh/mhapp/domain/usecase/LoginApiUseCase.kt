package com.mh.mhapp.domain.usecase

import com.mh.mhapp.data.model.LoginRequest
import com.mh.mhapp.domain.repository.LoginRepository

// class LoginApiUseCase @Inject constructor(private val loginRepository: LoginRepository) {
class LoginApiUseCase {

    private val loginRepository = LoginRepository()

    suspend operator fun invoke(loginRequest: LoginRequest) = loginRepository.login(loginRequest)
}
