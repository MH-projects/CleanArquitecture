package com.mh.mhapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mh.mhapp.data.local.room.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE user = :user LIMIT 1")
    suspend fun getUser(user: String): UserEntity

    @Query("SELECT name FROM user WHERE user = :userParameter")
    suspend fun getName(userParameter: String): String

    @Query("DELETE FROM user")
    suspend fun deleteUser()

    /*@Query("UPDATE user SET password = :newPassword WHERE user = :userParameter")
    suspend fun updatePassword(userParameter: String, newPassword: String)*/
}
