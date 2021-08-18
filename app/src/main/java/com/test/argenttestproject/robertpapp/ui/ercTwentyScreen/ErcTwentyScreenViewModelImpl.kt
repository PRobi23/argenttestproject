package com.test.argenttestproject.robertpapp.ui.ercTwentyScreen

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.argenttestproject.robertpapp.RxViewModel
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepository
import com.test.argenttestproject.robertpapp.data.remote.response.etherscan.EtherscanRepository
import com.test.argenttestproject.robertpapp.data.remote.response.etherscan.EtherscanResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.BiConsumer
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class ErcTwentyScreenViewModelImpl(
    private val ethplorerTokenRepository: EthplorerTokenRepository,
    private val etherscanRepository: EtherscanRepository
) : ErcTwentyScreenViewModel, RxViewModel() {

    override val tokenValues: MutableLiveData<MutableList<TokenValueResult>> =
        MutableLiveData()

    private val tokenValue = mutableListOf<TokenValueResult>()

    override val ercTwentyTokenSearchListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.e("Text changed to: $newText")
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { nonNullSymbol ->
                    tokenValue.clear()
                    ethplorerTokenRepository.getEthplorerTokenAddressessBySymbol(
                        nonNullSymbol
                    ).flatMapIterable {
                        it
                    }.flatMapSingle { symbolWithAddress ->
                        val symbol = symbolWithAddress.first
                        val address = symbolWithAddress.second

                        etherscanRepository.getHistoricalBalance(
                            address
                        ).map {
                            TokenValueResult(
                                symbolName = symbol,
                                balance = it.result.toDouble()
                            )

                        }
                    }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                            onNext = {
                                tokenValue.add(it)
                                tokenValues.postValue(tokenValue)
                                Timber.i("Query:  $query, Result: $it")
                            },
                            onError = {
                                Timber.e(it)
                            }
                        ).disposeOnCleared()
                }

                return false
            }
        }

}
