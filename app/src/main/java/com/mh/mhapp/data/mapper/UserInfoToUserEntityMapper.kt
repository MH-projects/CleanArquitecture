package com.mh.mhapp.data.mapper

import com.mh.mhapp.data.local.room.entity.UserEntity
import com.mh.mhapp.data.model.UserInfo

class UserInfoToUserEntityMapper(private val userLogin: String) : Transform<UserInfo, UserEntity>() {

    override fun transform(value: UserInfo): UserEntity =
        with(value) {
            UserEntity(
                user = userLogin,
                name = user.name,
                lastname = user.lastname,
                secondLastName = user.secondLastName,
                birthday = user.birthday,
                email = user.email,
                gender = user.gender,
                state = user.state,
                phone = user.phone
            )
        }
}
