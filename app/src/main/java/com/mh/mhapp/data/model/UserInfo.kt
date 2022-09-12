package com.mh.mhapp.data.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("name")
    var name: String,

    @SerializedName("lastname")
    var lastname: String,

    @SerializedName("secondLastName")
    var secondLastName: String,

    @SerializedName("birthday")
    var birthday: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("gender")
    var gender: String,

    @SerializedName("state")
    var state: String,

    @SerializedName("phone")
    var phone: String
)

data class UserInfo(

    @SerializedName("status")
    var status: Boolean,

    @SerializedName("user")
    var user: User,

    @SerializedName("message")
    var message: String = ""
)
