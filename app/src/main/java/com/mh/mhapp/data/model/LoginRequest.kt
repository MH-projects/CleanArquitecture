package com.mh.mhapp.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("user")
    val user: String,

    @SerializedName("pass")
    val pass: String
)
