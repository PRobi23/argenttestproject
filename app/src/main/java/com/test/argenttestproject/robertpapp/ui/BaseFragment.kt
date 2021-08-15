package com.test.argenttestproject.robertpapp.ui

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseFragment : Fragment() {

    private val disposables = CompositeDisposable()

    fun Disposable.disposeOnDestroy() = disposables.add(this)

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
