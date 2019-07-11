package com.tomergoldst.mediaapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tomergoldst.mediaapp.models.Entry
import com.tomergoldst.mediaapp.utils.dp2Px
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val mViewModel by viewModel<MainViewModel>()

    private lateinit var mAdapter: EntriesListAdapter

    private val adapterListener = object : EntriesListAdapter.OnAdapterInteractionListener {
        override fun onItemClicked(entry: Entry) {
            if (entry.type.isLink()) {
                mViewModel.onLinkClicked(entry)
            } else {
                mViewModel.onVideoClicked(entry)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.tomergoldst.mediaapp.R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMainClpb.show()

        mAdapter = EntriesListAdapter(adapterListener)
        fragmentMainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SimpleItemDecoration(context.dp2Px(8), context.dp2Px(16)))
            adapter = mAdapter
        }

        mViewModel.entries.observe(viewLifecycleOwner, Observer {
            fragmentMainClpb.hide()
            mAdapter.submitList(it)
            mAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    fragmentMainRecyclerView.scrollToPosition(0)

                }

                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                    fragmentMainRecyclerView.scrollToPosition(0)
                }
            })
        })

        mViewModel.eventOpenLink.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                openLink()
            }
        })

        mViewModel.eventPlayVideo.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                playVideo()
            }
        })

        fragmentMainSearchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mViewModel.onQueryTextChange(newText)
                    return false
                }
            })
        }

    }

    private fun openLink() {
        startActivity(Intent(context, WebViewActivity::class.java)
            .also { it.putExtra(WebViewActivity.EXTRA_URL, mViewModel.selectedEntry?.link?.href) }
            .also { it.putExtra(WebViewActivity.EXTRA_TITLE, mViewModel.selectedEntry?.title) })
        mViewModel.onLinkClickedCompleted()
    }

    private fun playVideo() {
        startActivity(Intent(context, VideoActivity::class.java)
            .also { it.putExtra(VideoActivity.EXTRA_URL, mViewModel.selectedEntry?.content?.src) }
            .also { it.putExtra(VideoActivity.EXTRA_TITLE, mViewModel.selectedEntry?.title) })
        mViewModel.onVideoClickedCompleted()

    }

    companion object {
        fun newInstance() = MainFragment()
    }

}
