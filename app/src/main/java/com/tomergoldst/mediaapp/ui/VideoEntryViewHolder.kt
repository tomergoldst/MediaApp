package com.tomergoldst.mediaapp.ui

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tomergoldst.mediaapp.GlideApp
import com.tomergoldst.mediaapp.R
import com.tomergoldst.mediaapp.config.Constants
import com.tomergoldst.mediaapp.models.Entry

class VideoEntryViewHolder(
    view: View,
    private val videoImageImv: AppCompatImageView = view.findViewById(R.id.videoEntryImv),
    private val playVideoImv: AppCompatImageView = view.findViewById(R.id.videoEntryPlayVideoImv),
    private val videoTitleTxv: AppCompatTextView = view.findViewById(R.id.videoEntryTitleTxv),
    private val videoSummaryTxv: AppCompatTextView = view.findViewById(R.id.videoEntrySummaryTxv)
) : RecyclerView.ViewHolder(view) {

    interface OnViewHolderInteractionListener{
        fun onItemClicked(entry: Entry)
    }

    fun bind(entry: Entry, listener: OnViewHolderInteractionListener) {
        val context = itemView.context

        videoTitleTxv.text = entry.title
        videoSummaryTxv.text = entry.summary

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.apply {
            strokeWidth = 8f
            centerRadius = 60f
            setColorFilter(
                ContextCompat.getColor(context, R.color.colorSecondary),
                PorterDuff.Mode.SRC_IN
            )
            start()
        }

        playVideoImv.isVisible = false

        val mediaItem = entry.getMediaGroupOfType(Constants.TYPE_IMAGE)?.getMediaItem(Constants.IMAGE_BASE)
        mediaItem?.let {
            GlideApp.with(context)
                .load(it.src)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_error_black_24dp)
                .addListener(ImageRequestListener())
                .into(videoImageImv)
        }

        itemView.setOnClickListener {
            listener.onItemClicked(entry)
        }
    }

    inner class ImageRequestListener : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return true
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            // show play video button
            playVideoImv.isVisible = true
            return false
        }
    }

    companion object {
        fun from(parent: ViewGroup): VideoEntryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_video_entry, parent, false)
            return VideoEntryViewHolder(view)
        }
    }

}