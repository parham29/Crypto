package com.pt29.crypto.Utils

import android.text.Spannable

import com.otaliastudios.autocomplete.AutocompletePolicy

/**
 * Created by parham on 2/22/2018.
 */

class SimplePolicy : AutocompletePolicy {
    override fun shouldShowPopup(text: Spannable, cursorPos: Int): Boolean {
        return text.length > 0
    }

    override fun shouldDismissPopup(text: Spannable, cursorPos: Int): Boolean {
        return text.length == 0
    }

    override fun getQuery(text: Spannable): CharSequence {
        return text
    }

    override fun onDismiss(text: Spannable) {}
}