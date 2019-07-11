package com.tomergoldst.mediaapp.models

import com.google.gson.annotations.SerializedName

data class MediaGroup(

    @SerializedName("type")
    var type: String,

    @SerializedName("media_item")
    var mediaItem: List<MediaItem>,

    @SerializedName("scale")
    var scale: String,

    @SerializedName("key")
    var key: String

){

    fun getMediaItem(key: String): MediaItem?{
        for (mediaItem in mediaItem){
            if (mediaItem.key == key){
                return mediaItem
            }
        }
        return null
    }

}
