package com.test.argenttestproject

import com.google.common.truth.Truth.assertThat
import com.test.argenttestproject.robertpapp.WalletAddressFormatter
import org.junit.Test

class WalletAddressFormatterTest {


    @Test
    fun `when format wallet address is called then it masks the middle of the text`() {
        val testWalletAddress = "123456"
        val expectedWalletAddress = "12**56"

        withSut {
            assertThat(
                formatWalletAddress(testWalletAddress)
            ).isEqualTo(expectedWalletAddress)
        }
    }

    @Test
    fun `when format wallet address is called with empty string then empty string is returned`() {
        withSut {
            assertThat(
                formatWalletAddress("")
            ).isEqualTo("")
        }
    }

    @Test
    fun `when format wallet address is called with two long string then string is returned`() {
        val testWalletAddress = "12"
        val expectedWalletAddress = "12"

        withSut {
            assertThat(
                formatWalletAddress(testWalletAddress)
            ).isEqualTo(expectedWalletAddress)
        }
    }

    @Test
    fun `when really long wallet address is called then the middle of the string is masked`() {
        val testWalletAddress = "123456789123456789123456789"
        val expectedWalletAddress = "12***********************89"

        withSut {
            assertThat(
                formatWalletAddress(testWalletAddress)
            ).isEqualTo(expectedWalletAddress)
        }
    }

    @Test
    fun `when wallet address contains five char then the middle is masked`() {
        val testWalletAddress = "12345"
        val expectedWalletAddress = "12*45"

        withSut {
            assertThat(
                formatWalletAddress(testWalletAddress)
            ).isEqualTo(expectedWalletAddress)
        }
    }

    private fun withSut(action: WalletAddressFormatter.() -> Unit) =
        WalletAddressFormatter.apply(action)
}
