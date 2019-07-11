package com.tomergoldst.mediaapp.models

import com.google.gson.annotations.SerializedName

data class Entry(

    @SerializedName("id")
    var id: String,

    @SerializedName("type")
    var type: Type,

    @SerializedName("title")
    var title: String,

    @SerializedName("summary")
    var summary: String,

    @SerializedName("published")
    var published: String,

    @SerializedName("media_group")
    var mediaGroup: List<MediaGroup>,

    @SerializedName("link")
    var link: Link?,

    @SerializedName("content")
    var content: Content?

){
    fun getMediaGroupOfType(type: String): MediaGroup?{
        for (mg in mediaGroup){
            if (mg.type == type){
                return mg
            }
        }
        return null
    }

}
