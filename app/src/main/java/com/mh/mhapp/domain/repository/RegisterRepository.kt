package com.mh.mhapp.domain.repository

import com.mh.mhapp.BuildConfig
import com.mh.mhapp.data.model.GenericResponse
import com.mh.mhapp.data.model.RegisterRequest
import com.mh.mhapp.data.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterRepository {

    suspend fun register(registerRequest: RegisterRequest): GenericResponse {
        return withContext(Dispatchers.IO) {
            Retrofit.api(BuildConfig.BASE_URL).register(registerRequest)
        }
    }
}
