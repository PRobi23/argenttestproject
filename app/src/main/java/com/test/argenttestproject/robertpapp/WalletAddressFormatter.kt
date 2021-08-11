package com.test.argenttestproject.robertpapp

object WalletAddressFormatter {
    private const val CHARACTER_MASK = "*"

    fun formatWalletAddress(walletAddress: String): String {
        if (walletAddress.length < 4) {
            return walletAddress
        }

        return walletAddress.mask(IntRange(2, walletAddress.length -3), CHARACTER_MASK)
    }
}
