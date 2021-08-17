package com.test.argenttestproject

import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepository
import com.test.argenttestproject.robertpapp.data.local.sharedRepository.SharedPreferenceRepository
import com.test.argenttestproject.robertpapp.ui.MainViewModelImpl
import com.test.argenttestproject.robertpapp.ui.MainViewModelImpl.Companion.API_KEY
import com.test.argenttestproject.robertpapp.ui.MainViewModelImpl.Companion.WALLET_ADDRESS
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class MainViewModelImplTest {

    private val mockSharedPreferencesRepository: SharedPreferenceRepository = mockk()

    private val mockEthplorerTokenRepository: EthplorerTokenRepository = mockk()

    @Before
    fun setUp(){
        every {
            mockSharedPreferencesRepository.setApiKey(API_KEY)
        } returns Single.just(true)

        every {
            mockSharedPreferencesRepository.setWalletAddress(WALLET_ADDRESS)
        } returns Single.just(true)
    }

    @Test
    fun `when setupInitialValues is called then set the given wallet address`() {

        withSut {
            setupInitialValues()

            verify {
                mockSharedPreferencesRepository.setWalletAddress(
                    WALLET_ADDRESS
                )
            }
        }
    }

    @Test
    fun `when setupInitialValues is called then set the given api key`() {
        withSut {
            setupInitialValues()

            verify {
                mockSharedPreferencesRepository.setApiKey(
                    API_KEY
                )
            }
        }
    }

    private fun withSut(action: MainViewModelImpl.() -> Unit) =
        MainViewModelImpl(
            sharedPreferenceRepository = mockSharedPreferencesRepository,
            ethplorerTokenRepository = mockEthplorerTokenRepository
        ).apply(action)
}
