package com.test.argenttestproject.robertpapp

/**
 * Mask the string with the given character (like *) in a range.
 *
 * @param range the range of the string characters where the mask is used.
 * @param maskChar the character used for mask the part of the string.
 */
fun String.mask(range: IntRange, maskChar: String): String {
    val builder = StringBuilder(this)

    for (i in 0 until length) {
        if (i in range) {
            builder.replace(i, i + 1, maskChar)
        }
    }

    return builder.toString()
}
