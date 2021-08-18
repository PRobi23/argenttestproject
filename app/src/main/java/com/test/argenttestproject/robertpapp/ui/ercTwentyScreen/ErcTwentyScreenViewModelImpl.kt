package com.test.argenttestproject.robertpapp.ui.ercTwentyScreen

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.argenttestproject.robertpapp.RxViewModel
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepository
import com.test.argenttestproject.robertpapp.data.remote.response.etherscan.EtherscanRepository
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class ErcTwentyScreenViewModelImpl(
    private val ethplorerTokenRepository: EthplorerTokenRepository,
    private val etherscanRepository: EtherscanRepository
) : ErcTwentyScreenViewModel, RxViewModel() {

    override val tokenValues: LiveData<MutableList<TokenValueResult>> =
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
                    ).flatMapIterable {
                        it
                    }.flatMapSingle { symbolWithAddress ->
                        val symbol = symbolWithAddress.first
                        val address = symbolWithAddress.second

                        etherscanRepository.getHistoricalBalance(
                            address
                        ).doOnSuccess { result ->
                            tokenValues.value?.add(
                                TokenValueResult(
                                    symbolName = symbol,
                                    balance = result.balance
                                )
                            )
                        }
                    }.subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onNext = {
                                Timber.i("Query:  $query, Result: $it")
                            }
                        ).disposeOnCleared()
                }

                return false
            }
        }

}
