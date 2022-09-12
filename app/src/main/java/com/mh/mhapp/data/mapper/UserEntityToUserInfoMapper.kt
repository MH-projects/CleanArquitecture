package com.mh.mhapp.data.mapper

import com.mh.mhapp.data.local.room.entity.UserEntity
import com.mh.mhapp.data.model.User
import com.mh.mhapp.data.model.UserInfo

class UserEntityToUserInfoMapper : Transform<UserEntity, UserInfo>() {

    override fun transform(value: UserEntity): UserInfo =
        with(value) {
            UserInfo(
                status = true,
                user = User(
                    name = name,
                    lastname = lastname,
                    secondLastName = secondLastName,
                    birthday = birthday,
                    email = email,
                    gender = gender,
                    state = state,
                    phone = phone
                )
            )
        }
}
