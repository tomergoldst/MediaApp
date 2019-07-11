package com.tomergoldst.mediaapp.models

import com.google.gson.annotations.SerializedName

data class Content(

    @SerializedName("src")
    var src: String,

    @SerializedName("type")
    var type: String

)
