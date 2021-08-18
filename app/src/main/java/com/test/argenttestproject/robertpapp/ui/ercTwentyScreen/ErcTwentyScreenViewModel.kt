package com.test.argenttestproject.robertpapp.ui.ercTwentyScreen

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface ErcTwentyScreenViewModel {
    val ercTwentyTokenSearchListener: SearchView.OnQueryTextListener

    val tokenValues: LiveData<MutableList<TokenValueResult>>
}
