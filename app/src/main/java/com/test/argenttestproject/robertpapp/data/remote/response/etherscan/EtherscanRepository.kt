package com.test.argenttestproject.robertpapp.data.remote.response.etherscan

import com.test.argenttestproject.robertpapp.ui.ercTwentyScreen.TokenValueResult
import io.reactivex.rxjava3.core.Single

interface EtherscanRepository {

    fun getHistoricalBalance(contactAddress: String) : Single<TokenValueResult>
}
