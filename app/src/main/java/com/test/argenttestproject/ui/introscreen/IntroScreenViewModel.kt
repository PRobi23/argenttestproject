package com.test.argenttestproject.ui.introscreen

import androidx.databinding.ObservableField

interface IntroScreenViewModel {

    val walletAddress: ObservableField<String>

    fun navigateToAccessTokens()
}
