package com.mh.mhapp.data.network

import com.mh.mhapp.data.model.GenericResponse
import com.mh.mhapp.data.model.LoginRequest
import com.mh.mhapp.data.model.RegisterRequest
import com.mh.mhapp.data.model.UserInfo
import com.mh.mhapp.data.network.Path.PATH_POST_INFO
import com.mh.mhapp.data.network.Path.PATH_POST_LOGIN
import com.mh.mhapp.data.network.Path.PATH_POST_REGISTER
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST(PATH_POST_LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): GenericResponse

    @POST(PATH_POST_REGISTER)
    suspend fun register(@Body registerRequest: RegisterRequest): GenericResponse

    @GET(PATH_POST_INFO)
    suspend fun info(@Path("user") user: String): UserInfo
}
