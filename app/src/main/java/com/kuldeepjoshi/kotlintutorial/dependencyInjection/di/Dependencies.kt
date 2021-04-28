package com.kuldeepjoshi.kotlintutorial.dependencyInjection

import com.kuldeepjoshi.kotlintutorial.dependencyInjection.storage.FakeInMemoryUsersRepository
import com.kuldeepjoshi.kotlintutorial.dependencyInjection.storage.IUserRepository
import org.koin.dsl.module

val appDependencies = module {
    single<IUserRepository> { FakeInMemoryUsersRepository() }
}