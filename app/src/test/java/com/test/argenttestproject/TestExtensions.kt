package com.test.argenttestproject

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.test.argenttestproject.ui.introscreen.IntroScreenFragment
import org.hamcrest.CoreMatchers

inline fun <reified T : Activity> withActivity(noinline action: (T) -> Unit) {
    ActivityScenario.launch(T::class.java).use { it.onActivity(action) }
}

inline fun <reified T : Fragment> withFragment(args: Bundle? = null, noinline action: T.() -> Unit) =
    FragmentScenario.launchInContainer(T::class.java, args, R.style.ThemeOverlay_AppCompat, null)
        .onFragment(action)

fun shouldBeDisplayed(): ViewAssertion = ViewAssertions.matches(
    CoreMatchers.anyOf(
        ViewMatchers.isDisplayed(),
        ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    )
)
