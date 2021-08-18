package com.test.argenttestproject.robertpapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.argenttestproject.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModelImpl by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.setupInitialValues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    setContentView(R.layout.main_activity)

                },
                onError = {
                    setContentView(R.layout.error_page)
                }
            )

    }
}
