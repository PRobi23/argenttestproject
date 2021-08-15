package com.test.argenttestproject.robertpapp.ui.introScreen

import androidx.databinding.ObservableField
import com.test.argenttestproject.robertpapp.RxViewModel
import com.test.argenttestproject.robertpapp.data.SharedPreferenceRepository
import com.test.argenttestproject.robertpapp.wrap
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.processors.PublishProcessor


class IntroScreenViewModelImpl(
    sharedPreferenceRepository: SharedPreferenceRepository
) : IntroScreenViewModel, RxViewModel() {

    override val walletAddress: ObservableField<String> = ObservableField()

    private val navigateToAccessTokensEventProcessor = PublishProcessor.create<Unit>()

    internal val navigateToAccessTokensEvents: Flowable<Unit> =
        navigateToAccessTokensEventProcessor.wrap()

    override fun navigateToAccessTokens() =
        navigateToAccessTokensEventProcessor.onNext(Unit)

    init {
        sharedPreferenceRepository.getWalletAddress()
            .subscribeBy(
                onError = {

                },
                onSuccess = { walletAddressValue ->
                    walletAddress.set(walletAddressValue)
                }
            ).disposeOnCleared()
    }
}
