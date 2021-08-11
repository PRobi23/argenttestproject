package com.test.argenttestproject.ui.di

import com.test.argenttestproject.ui.introscreen.IntroScreenViewModelImpl
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {

    viewModel { IntroScreenViewModelImpl() }
}
