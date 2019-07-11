package com.tomergoldst.mediaapp.models

import com.google.gson.annotations.SerializedName

data class MediaResponse(

    @SerializedName("id")
    var id: String,

    @SerializedName("entry")
    var entry: List<Entry>
)
