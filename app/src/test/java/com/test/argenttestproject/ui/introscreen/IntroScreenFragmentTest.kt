package com.test.argenttestproject.ui.introscreen

import android.app.Application
import android.os.Build
import android.os.Looper
import androidx.databinding.ObservableField
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.argenttestproject.*
import com.test.argenttestproject.ui.MainActivity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.intro_screen_fragment.*
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


@Config(
    sdk = [Build.VERSION_CODES.O_MR1],
    application = KoinTestApp::class,
    manifest = Config.NONE
)
@RunWith(AndroidJUnit4::class)
class IntroScreenFragmentTest : BaseActivityTest() {

    private val walletAddress = "WalletAddress"

    class App : Application(), KoinTest {
        lateinit var introScreenViewModel: IntroScreenViewModelImpl

        override fun onCreate() {
            super.onCreate()

            introScreenViewModel = mockk()

            val testModule =
                module {
                    viewModel {
                        introScreenViewModel
                    }
                }

            loadKoinModules(testModule)

            every {
                introScreenViewModel.walletAddress
            } returns ObservableField("WalletAddress")
        }
    }

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
