package com.mh.mhapp.domain.repository

import com.mh.mhapp.BuildConfig
import com.mh.mhapp.data.model.LoginRequest
import com.mh.mhapp.data.model.GenericResponse
import com.mh.mhapp.data.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*@Singleton
class LoginRepository @Inject constructor(
    @ApplicationContext val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return if (context.isOnline()) {
            withContext(Dispatchers.IO) {
                Retrofit.api(BuildConfig.BASE_URL).login(loginRequest)
            }
        } else {
            LoginResponse(false, "No hay internet")
        }
    }
}*/

class LoginRepository {

    suspend fun login(loginRequest: LoginRequest): GenericResponse {

        return withContext(Dispatchers.IO) {
            Retrofit.api(BuildConfig.BASE_URL).login(loginRequest)
        }
    }
}
