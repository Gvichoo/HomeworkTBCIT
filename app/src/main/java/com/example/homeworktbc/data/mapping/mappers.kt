package com.example.homeworktbc.data.mapping

import com.example.homeworktbc.data.entity.User
import com.example.homeworktbc.data.remote.response.UserData

fun UserData.toUser(): User {
    return User(id = this.id,email=this.email,firstName=this.firstName,lastName=this
        .lastName,avatar = this.avatar,)
}
