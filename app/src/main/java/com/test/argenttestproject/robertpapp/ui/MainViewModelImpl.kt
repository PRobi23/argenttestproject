package com.test.argenttestproject.robertpapp.ui

import com.test.argenttestproject.robertpapp.RxViewModel
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepository
import com.test.argenttestproject.robertpapp.data.local.sharedRepository.SharedPreferenceRepository
import io.reactivex.rxjava3.core.Completable

class MainViewModelImpl(
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val ethplorerTokenRepository: EthplorerTokenRepository
) : RxViewModel(), MainViewModel {

    override fun setupInitialValues(): Completable {
        return sharedPreferenceRepository.setWalletAddress(
            WALLET_ADDRESS
        ).ignoreElement().andThen(
            sharedPreferenceRepository.setApiKey(
                API_KEY
            )
        ).ignoreElement().andThen(
            ethplorerTokenRepository.updateEthplorerTokens()
        )
    }

    companion object {
        const val WALLET_ADDRESS = "0xde57844f758a0a6a1910a4787ab2f7121c8978c3"
        const val API_KEY = "E5QFXD7ZYRH7THQM5PIXB8JD4GY38SEJZ4"
    }
}
