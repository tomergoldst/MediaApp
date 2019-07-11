package com.tomergoldst.mediaapp.models

import com.google.gson.annotations.SerializedName

data class Link(

    @SerializedName("rel")
    var rel: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("href")
    var href: String

)
