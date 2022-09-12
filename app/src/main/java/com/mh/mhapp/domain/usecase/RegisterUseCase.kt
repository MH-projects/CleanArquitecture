package com.mh.mhapp.domain.usecase

import com.mh.mhapp.data.model.RegisterRequest
import com.mh.mhapp.domain.repository.RegisterRepository

class RegisterUseCase {

    private val registerRepository = RegisterRepository()

    suspend operator fun invoke(registerRequest: RegisterRequest) =
        registerRepository.register(registerRequest)
}
