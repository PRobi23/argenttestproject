package com.test.argenttestproject.robertpapp

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class RxViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    fun Disposable.disposeOnCleared() = disposables.add(this)

    public override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
