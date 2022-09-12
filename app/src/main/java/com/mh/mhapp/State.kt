package com.mh.mhapp

sealed class State {

    object Loading : State()

    object Success : State()

    data class Failure(val message: String) : State()
}
