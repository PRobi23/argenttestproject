package com.test.argenttestproject.robertpapp.ui.introScreen

import com.test.argenttestproject.robertpapp.data.SharedPreferenceRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.Before


class IntroScreenViewModelTest {

    private val mockSharedPreferencesRepository: SharedPreferenceRepository = mockk()

    private val testWalletAddress = "TEST_WALLET_ADDRESS"

    @Before
    fun setUp() {
        every {
            mockSharedPreferencesRepository.getWalletAddress()
        } returns Single.just(testWalletAddress)
    }

    @Test
    fun `wallet address is set by shared preferences repository`() {
        withSut {
            assertThat(walletAddress.get())
                .isEqualTo(testWalletAddress)
        }
    }

    @Test
    fun `when users clicks on navigate to access tokens button then navigate to access tokens gets fired`() {

        withSut {
            val testSubscriber = navigateToAccessTokensEvents.test()

            navigateToAccessTokens()

            testSubscriber
                .assertValueCount(1)
                .assertNoErrors()
        }
    }

    private fun withSut(action: IntroScreenViewModelImpl.() -> Unit) =
        IntroScreenViewModelImpl(
            sharedPreferenceRepository = mockSharedPreferencesRepository
        ).apply(action)
}
