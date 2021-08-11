package com.test.argenttestproject.data

import android.content.SharedPreferences
import com.test.argenttestproject.robertpapp.data.SharedPreferenceRepositoryImpl
import com.test.argenttestproject.robertpapp.data.SharedPreferenceRepositoryImpl.Companion.WALLET_ADDRESS
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SharedPreferenceRepositoryTest {

    private val mockSharedPreferences: SharedPreferences = mockk()

    private val mockSharedPreferencesEditor: SharedPreferences.Editor = mockk()

    private val testWalletAddress = "TEST WALLET ADDRESS"

    @Test
    fun `when set wallet address is called then put string is called with commit on editor returns true is save is successful`() {
        mockSetWalletAddress(true)

        withSut {
            val testSubscriber = setWalletAddress(testWalletAddress).test()

            verifySetWalletAddressCalls()

            testSubscriber
                .assertNoErrors()
                .assertValue(true)
                .assertComplete()
        }
    }

    @Test
    fun `when set wallet address is called then put string is called with commit on editor returns false is save has error`() {
        mockSetWalletAddress(false)

        withSut {
            val testSubscriber = setWalletAddress(testWalletAddress).test()

            verifySetWalletAddressCalls()

            testSubscriber
                .assertNoErrors()
                .assertValue(false)
                .assertComplete()
        }
    }

    @Test
    fun `get wallet address is called then value is returned from shared preference`(){
        every {
            mockSharedPreferences.getString(WALLET_ADDRESS, "")
        } returns testWalletAddress

        withSut {
            val testSubscriber = getWalletAddress().test()

            testSubscriber
                .assertNoErrors()
                .assertValue(testWalletAddress)
                .assertComplete()
        }
    }

    private fun mockSetWalletAddress(saveSuccess: Boolean) {
        every {
            mockSharedPreferencesEditor.putString(WALLET_ADDRESS, testWalletAddress)
        } returns mockSharedPreferencesEditor

        every {
            mockSharedPreferencesEditor.commit()
        } returns saveSuccess
    }

    private fun verifySetWalletAddressCalls() {
        verify {
            mockSharedPreferencesEditor.putString(WALLET_ADDRESS, testWalletAddress)
        }

        verify {
            mockSharedPreferencesEditor.commit()
        }
    }

    private fun withSut(action: SharedPreferenceRepositoryImpl.() -> Unit) =
        SharedPreferenceRepositoryImpl(
            sharedPreference = mockSharedPreferences,
            sharedPreferenceEditor = mockSharedPreferencesEditor
        ).apply(action)

}
