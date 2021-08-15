package com.test.argenttestproject.robertpapp.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.test.argenttestproject.robertpapp.WalletAddressFormatter

@BindingAdapter("maskWalletAddress")
fun bindWalletAddressValue(view: TextView, walletAddress: String) {
    if (walletAddress.isEmpty().not()) {
        view.text =
            WalletAddressFormatter.formatWalletAddress(walletAddress)
    }
}
