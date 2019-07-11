package com.tomergoldst.mediaapp.ui

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.tomergoldst.mediaapp.GlideApp
import com.tomergoldst.mediaapp.R
import com.tomergoldst.mediaapp.config.Constants
import com.tomergoldst.mediaapp.models.Entry

class LinkEntryViewHolder(
    view: View,
    private val linkImageImv: AppCompatImageView = view.findViewById(R.id.linkEntryImv),
    private val linkTitleTxv: AppCompatTextView = view.findViewById(R.id.linkEntryTitleTxv),
    private val linkSummaryTxv: AppCompatTextView = view.findViewById(R.id.linkEntrySummaryTxv)
) : RecyclerView.ViewHolder(view) {

    interface OnViewHolderInteractionListener{
        fun onItemClicked(entry: Entry)
    }

    fun bind(entry: Entry, listener: OnViewHolderInteractionListener) {
        val context = itemView.context

        linkTitleTxv.text = entry.title
        linkSummaryTxv.text = entry.summary

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

        val mediaItem = entry.getMediaGroupOfType(Constants.TYPE_IMAGE)?.getMediaItem(Constants.IMAGE_BASE)
        mediaItem?.let {
            GlideApp.with(context)
                .load(it.src)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_error_black_24dp)
                .into(linkImageImv)
        }

        itemView.setOnClickListener {
            listener.onItemClicked(entry)
        }
    }

    companion object {
        fun from(parent: ViewGroup): LinkEntryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_link_entry, parent, false)
            return LinkEntryViewHolder(view)
        }
    }

}