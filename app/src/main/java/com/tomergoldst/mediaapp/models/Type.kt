package com.tomergoldst.mediaapp.models

import com.google.gson.annotations.SerializedName
import com.tomergoldst.mediaapp.config.Constants

data class Type(

    @SerializedName("value")
    var value: String
) {
    fun isLink(): Boolean = value == Constants.TYPE_LINK

    fun isVideo(): Boolean = value == Constants.TYPE_VIDEO
}

