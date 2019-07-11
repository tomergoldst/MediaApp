package com.tomergoldst.mediaapp.models

import com.google.gson.annotations.SerializedName

data class MediaItem(

    @SerializedName("src")
    var src: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("scale")
    var scale: String,

    @SerializedName("key")
    var key: String

)
