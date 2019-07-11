package com.tomergoldst.mediaapp.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tomergoldst.mediaapp.data.remote.Constants
import com.tomergoldst.mediaapp.models.Entry
import java.lang.RuntimeException

class EntriesListAdapter(private val listener: OnAdapterInteractionListener) :
    ListAdapter<Entry, RecyclerView.ViewHolder>(DiffCallback()) {

    interface OnAdapterInteractionListener{
        fun onItemClicked(entry: Entry)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type.value) {
            Constants.TYPE_LINK -> 0
            Constants.TYPE_VIDEO -> 1
            else -> throw RuntimeException("Unknown adapter item type")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> LinkEntryViewHolder.from(parent)
            1 -> VideoEntryViewHolder.from(parent)
            else -> throw RuntimeException("Unknown adapter item type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> {
                val linkEntryViewHolder = holder as LinkEntryViewHolder
                linkEntryViewHolder.bind(getItem(holder.adapterPosition), object : LinkEntryViewHolder.OnViewHolderInteractionListener{
                    override fun onItemClicked(entry: Entry) {
                        listener.onItemClicked(entry)
                    }
                })

            }
            1 -> {
                val videoEntryViewHolder = holder as VideoEntryViewHolder
                videoEntryViewHolder.bind(getItem(holder.adapterPosition), object : VideoEntryViewHolder.OnViewHolderInteractionListener{
                    override fun onItemClicked(entry: Entry) {
                        listener.onItemClicked(entry)
                    }
                })
            }
            else -> throw RuntimeException("Unknown adapter item type")
        }
    }

    companion object {
        class DiffCallback : DiffUtil.ItemCallback<Entry>() {
            override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
                return oldItem == newItem
            }
        }

    }

}
