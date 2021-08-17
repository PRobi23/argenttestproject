package com.test.argenttestproject.data.local.ethplorerToken

import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenDao
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenEntity
import com.test.argenttestproject.robertpapp.data.local.ethplorerToken.EthplorerTokenRepositoryImpl
import com.test.argenttestproject.robertpapp.data.remote.api.EthplorerApi
import com.test.argenttestproject.robertpapp.data.remote.response.EthplorerToken
import com.test.argenttestproject.robertpapp.data.remote.response.EthplorerTokenPrice
import com.test.argenttestproject.robertpapp.data.remote.response.EthplorerTokens
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class EthplorerTokenRepositoryImplTest {

    private val mockEthplorerApi: EthplorerApi = mockk()

    private val mockEthplorerTokenDao: EthplorerTokenDao = mockk()

    private val testData = EthplorerToken(
        address = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2",
        name = "WETH",
        decimals = 18,
        symbol = "WETH",
        totalSupply = "6744048061935789138168198",
        owner = "0x",
        txsCount = 501127,
        transfersCount = 65488277,
        lastUpdated = 1629202600,
        slot = 3,
        issuancesCount = 13942675,
        holdersCount = 221009,
        image = "/images/weth.png",
        website = "https://weth.io/",
        coingecko = "weth",
        ethTransfersCount = 194215,
        opCount = 8343600
    )

    private val testEntityData = EthplorerTokenEntity(
        address = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2",
        name = "WETH",
        decimals = 18,
        symbol = "WETH",
        totalSupply = "6744048061935789138168198",
        owner = "0x",
        txsCount = 501127,
        transfersCount = 65488277,
        lastUpdated = 1629202600,
        slot = 3,
        issuancesCount = 13942675,
        holdersCount = 221009,
        image = "/images/weth.png",
        website = "https://weth.io/",
        coingecko = "weth",
        ethTransfersCount = 194215,
        opCount = 8343600
    )

    @Test
    fun `when update update etphlorerTokens are called then tokens are downloaded and set to the database after cleaning it`() {

        every {
            mockEthplorerTokenDao.deleteAll()
        } returns Completable.complete()

        every {
            mockEthplorerApi.getTopTokens()
        } returns Single.just(EthplorerTokens(listOf(testData)))

        every {
            mockEthplorerTokenDao.insertAll(
                listOf(testEntityData)
            )
        } returns Completable.complete()

        withSut {
            val testSubscriber = updateEthplorerTokens().test()

            verify {
                mockEthplorerTokenDao.deleteAll()
            }
            verify {
                mockEthplorerTokenDao.insertAll(listOf(testEntityData))
            }
            verify {
                mockEthplorerApi.getTopTokens()
            }

            testSubscriber
                .assertNoErrors()
                .assertComplete()
        }
    }

    @Test
    fun `when delete failed then update ethplorer tokens completable fails`() {

        every {
            mockEthplorerTokenDao.deleteAll()
        } returns Completable.error(RuntimeException())

        every {
            mockEthplorerApi.getTopTokens()
        } returns Single.just(EthplorerTokens(listOf(testData)))

        every {
            mockEthplorerTokenDao.insertAll(
                listOf(testEntityData)
            )
        } returns Completable.complete()

        withSut {
            val testSubscriber = updateEthplorerTokens().test()

            verify {
                mockEthplorerTokenDao.deleteAll()
            }

            testSubscriber
                .assertError(RuntimeException::class.java)
        }
    }

    @Test
    fun `when insert failed then update ethplorer tokens completable fails`() {

        every {
            mockEthplorerTokenDao.deleteAll()
        } returns Completable.complete()

        every {
            mockEthplorerApi.getTopTokens()
        } returns Single.just(EthplorerTokens(listOf(testData)))

        every {
            mockEthplorerTokenDao.insertAll(
                listOf(testEntityData)
            )
        } returns Completable.error(RuntimeException())

        withSut {
            val testSubscriber = updateEthplorerTokens().test()

            verify {
                mockEthplorerTokenDao.deleteAll()
            }
            verify {
                mockEthplorerTokenDao.insertAll(listOf(testEntityData))
            }
            verify {
                mockEthplorerApi.getTopTokens()
            }

            testSubscriber
                .assertError(RuntimeException::class.java)
        }
    }

    private fun withSut(action: EthplorerTokenRepositoryImpl.() -> Unit) =
        EthplorerTokenRepositoryImpl(
            ethplorerApi = mockEthplorerApi,
            ethplorerTokenDao = mockEthplorerTokenDao
        ).apply(action)
}
