package com.kuldeepjoshi.kotlintutorial.dependencyInjection.storage

import android.annotation.SuppressLint
import android.util.Log
import com.kuldeepjoshi.kotlintutorial.dependencyInjection.domain.User
import java.text.SimpleDateFormat

class FakeInMemoryUsersRepository : IUserRepository {

    val TAG = "FakeInMemoryUsersRepository"

    override fun getById(userId: Int): User {
        return User(
            "1",
            "Kuls",
            "Joshi",
            SimpleDateFormat("dd/mm/yyyy").parse("18/04/1994")!!
        )
    }

    @SuppressLint("LongLogTag")
    override fun save(user: User) {
        Log.i(TAG, "save: $user")
    }
}