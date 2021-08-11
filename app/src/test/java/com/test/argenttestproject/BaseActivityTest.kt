package com.test.argenttestproject

import android.app.Application
import androidx.test.core.app.ApplicationProvider

abstract class BaseActivityTest {
    fun <T : Application> application(): T = ApplicationProvider.getApplicationContext() as T
}

