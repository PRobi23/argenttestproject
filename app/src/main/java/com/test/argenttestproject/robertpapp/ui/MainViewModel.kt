package com.test.argenttestproject.robertpapp.ui

import io.reactivex.rxjava3.core.Completable

interface MainViewModel {
    fun setupInitialValues() : Completable
}
