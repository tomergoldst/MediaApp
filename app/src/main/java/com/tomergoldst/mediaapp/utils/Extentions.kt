package com.tomergoldst.mediaapp.utils

import android.content.Context
import android.util.TypedValue

fun Context.dp2Px(value: Float): Float{
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        resources.displayMetrics
    )
}

fun Context.dp2Px(value: Int): Int{
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(),
        resources.displayMetrics
    ).toInt()
}

