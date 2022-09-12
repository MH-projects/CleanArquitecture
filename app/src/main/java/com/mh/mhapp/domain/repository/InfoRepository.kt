package com.mh.mhapp.domain.repository

import android.content.Context
import com.mh.mhapp.BuildConfig
import com.mh.mhapp.data.local.room.DataBase
import com.mh.mhapp.data.mapper.UserEntityToUserInfoMapper
import com.mh.mhapp.data.mapper.UserInfoToUserEntityMapper
import com.mh.mhapp.data.model.UserInfo
import com.mh.mhapp.data.network.Retrofit
import com.mh.mhapp.utils.Utils.isOnline

class InfoRepository {

    suspend fun getInfoUser(ctx: Context, user: String): UserInfo {
        return if (ctx.isOnline()) {
            val infoResult = Retrofit.api(BuildConfig.BASE_URL).info(user)

            val entityMapper = UserInfoToUserEntityMapper(user).transform(infoResult)
            // entityMapper.user = user

            DataBase(ctx).getDB().daoUser().deleteUser()
            DataBase(ctx).getDB().daoUser().insertUser(entityMapper)

            infoResult
        } else {
            getLocalInfoUser(ctx, user)
        }
    }

    suspend fun getLocalInfoUser(ctx: Context, user: String): UserInfo {
        val userEntity = DataBase(ctx).getDB().daoUser().getUser(user)
        return UserEntityToUserInfoMapper().transform(userEntity)
    }
}
