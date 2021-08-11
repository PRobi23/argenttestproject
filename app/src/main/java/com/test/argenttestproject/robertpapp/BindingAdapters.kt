package com.test.argenttestproject.robertpapp

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("maskWalletAddress")
fun bindWalletAddressValue(view: TextView, walletAddress: String) {
    if (walletAddress.isEmpty().not()) {
        view.text =
            WalletAddressFormatter.formatWalletAddress(walletAddress)
    }
}
