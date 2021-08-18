package com.test.argenttestproject.robertpapp.ui.ercTwentyScreen

import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepository
import io.mockk.mockk

class ErcTwentyScreenViewModelImplTest {

    private val mockEthplorerTokenRepository: EthplorerTokenRepository = mockk()




    private fun withSut(action: ErcTwentyScreenViewModelImpl.() -> Unit) =
        ErcTwentyScreenViewModelImpl(
            ethplorerTokenRepository = mockEthplorerTokenRepository
        ).apply(action)
}
