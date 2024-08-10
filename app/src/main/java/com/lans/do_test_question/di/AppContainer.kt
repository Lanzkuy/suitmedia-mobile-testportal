package com.lans.do_test_question.di

import android.content.Context
import com.lans.do_test_question.network.ApiService
import com.lans.do_test_question.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface IAppContainer {
    val userRepository: UserRepository
}

class AppContainer(
    private val context: Context
) : IAppContainer {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    override val userRepository: UserRepository by lazy { UserRepository(retrofit.create(ApiService::class.java)) }
}

