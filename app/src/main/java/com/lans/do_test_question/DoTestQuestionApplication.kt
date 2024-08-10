package com.lans.do_test_question

import android.app.Application
import com.lans.do_test_question.di.AppContainer

class DoTestQuestionApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(context = applicationContext)
    }
}