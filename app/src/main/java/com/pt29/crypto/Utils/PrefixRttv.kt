package com.pt29.crypto.Utils

import android.content.Context
import android.util.AttributeSet

import com.github.curioustechizen.ago.RelativeTimeTextView
import com.pt29.crypto.R

internal class PrefixRttv : RelativeTimeTextView {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun getRelativeTimeDisplayString(referenceTime: Long, now: Long): CharSequence {
        val relativeTime = super.getRelativeTimeDisplayString(referenceTime, now) as String
        return resources.getString(R.string.format_relative_time_with_prefix, relativeTime)
    }
}