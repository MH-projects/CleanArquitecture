package com.mh.mhapp

enum class TypeError {
    USUARIO_CONTRASENA,
    INTERNET,
    SERVER
}

data class CustomError(
    val mensaje: String,
    val code: Int,
    val type: TypeError
)

sealed class LoginSate {

    object Cargando : LoginSate()

    object Exitoso : LoginSate()

    data class Error(val mensaje: String) : LoginSate()
}
