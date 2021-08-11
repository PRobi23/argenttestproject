package com.test.argenttestproject.robertpapp.introScreen

import androidx.databinding.ObservableField

interface IntroScreenViewModel {

    /**
     * The wallet address of the app. Currently it's const but later it can be changed
     */
    val walletAddress: ObservableField<String>

    /**
     * Navigate to access tokens page
     */
    fun navigateToAccessTokens()
}
