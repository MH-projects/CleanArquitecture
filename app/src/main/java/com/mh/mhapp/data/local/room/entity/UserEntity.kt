package com.mh.mhapp.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mh.mhapp.data.local.room.DBConstant

@Entity(tableName = DBConstant.TB_NAME_USER)
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "user")
    var user: String = "",

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "lastname")
    var lastname: String,

    @ColumnInfo(name = "secondLastName")
    var secondLastName: String,

    @ColumnInfo(name = "birthday")
    var birthday: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "state")
    var state: String,

    @ColumnInfo(name = "phone")
    var phone: String
)
