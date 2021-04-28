package com.kuldeepjoshi.kotlintutorial.dependencyInjection.domain

import java.util.*

data class User(
    val userId: String,
    val name: String,
    val surname: String,
    val birthDate: Date
)
