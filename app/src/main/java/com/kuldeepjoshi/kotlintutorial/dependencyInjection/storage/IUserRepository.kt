package com.kuldeepjoshi.kotlintutorial.dependencyInjection.storage

import com.kuldeepjoshi.kotlintutorial.dependencyInjection.domain.User

interface IUserRepository {
    fun getById(userId: Int): User
    fun save(user: User)
}