package com.test.argenttestproject.robertpapp.ui.ercTwentyScreen

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.argenttestproject.robertpapp.RxViewModel
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepository
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class ErcTwentyScreenViewModelImpl(
    private val ethplorerTokenRepository: EthplorerTokenRepository
) : ErcTwentyScreenViewModel, RxViewModel() {

    override val tokenValues: LiveData<List<TokenValueResult>> =
        MutableLiveData()

    override val ercTwentyTokenSearchListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.e("Text changed to: $newText")
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { nonNullSymbol ->
                    ethplorerTokenRepository.getEthplorerTokenAddressessBySymbol(
                        nonNullSymbol
                    )
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onNext = {
                                val i = 2
                            }
                        ).disposeOnCleared()
                }

                return false
            }
        }

}
