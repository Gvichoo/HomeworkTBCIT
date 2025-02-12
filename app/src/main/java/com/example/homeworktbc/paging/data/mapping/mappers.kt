package com.example.homeworktbc.paging.data.mapping

import com.example.homeworktbc.paging.data.entity.User
import com.example.homeworktbc.paging.data.retro.UserData

fun UserData.toUser(): User {
    return User(id = this.id,email=this.email,firstName=this.firstName,lastName=this
        .lastName,avatar = this.avatar,)
}
