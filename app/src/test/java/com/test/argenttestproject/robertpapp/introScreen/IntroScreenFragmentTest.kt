package com.test.argenttestproject.robertpapp.introScreen

import android.os.Looper
import androidx.databinding.ObservableField
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.argenttestproject.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.intro_screen_fragment.*
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = KoinTestApp::class, sdk = [28])
class IntroScreenFragmentTest : BaseActivityTest() {

    private val walletAddress = "WalletAddress"

    @After
    fun tearDown() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
    }

    val app: KoinTestApp = ApplicationProvider.getApplicationContext()

    @Test
    fun `WHEN navigate to access button is clicked THEN the view model navigateToAccessTokens gets called and its name is Navigate to access tokens`() {
        val mockIntroScreenViewModel: IntroScreenViewModelImpl = mockk()
        val modules = module {
            viewModel {
                mockIntroScreenViewModel
            }
        }

        app.loadModules(modules) {
            withFragment<IntroScreenFragment> {
                onView(withId(R.id.intro_screen_navigateToAccessTokensButton)).check(
                    shouldBeDisplayed()
                )
                intro_screen_navigateToAccessTokensButton.performClick()

                verify {
                    mockIntroScreenViewModel.navigateToAccessTokens()
                }
            }
        }
    }

    @Test
    fun `WHEN walletAddress is set THEN the value is visible in the view`() {
        val mockIntroScreenViewModel: IntroScreenViewModelImpl = mockk()
        val modules = module {
            viewModel {
                mockIntroScreenViewModel
            }
        }
        every {
            mockIntroScreenViewModel.walletAddress
        } returns ObservableField(walletAddress)

        app.loadModules(modules) {
            withFragment<IntroScreenFragment> {
                onView(withId(R.id.intro_screen_wallet_address_title)).check(shouldBeDisplayed())
                onView(withId(R.id.intro_screen_wallet_address_value)).check(shouldBeDisplayed())

                assertEquals(walletAddress, intro_screen_wallet_address_value.text)
            }
        }
    }
}
