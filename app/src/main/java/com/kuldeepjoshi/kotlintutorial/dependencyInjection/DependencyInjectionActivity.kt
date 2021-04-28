package com.kuldeepjoshi.kotlintutorial.dependencyInjection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kuldeepjoshi.kotlintutorial.R
import com.kuldeepjoshi.kotlintutorial.dependencyInjection.domain.User
import com.kuldeepjoshi.kotlintutorial.dependencyInjection.storage.IUserRepository
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat

class DependencyInjectionActivity : AppCompatActivity() {

    private val userRepository: IUserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dependency_injection)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            getAndSaveCurrentUserData()
        }
    }

    private fun getAndSaveCurrentUserData() {

        val user = User(
            userId = "1",
            name = "Neha",
            surname = "Joshi",
            birthDate = SimpleDateFormat("dd/MM/yyyy").parse("18/01/1993")
        )

        userRepository.save(user)

    }
}