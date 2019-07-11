package com.tomergoldst.mediaapp.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import java.lang.RuntimeException
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.activity_web_view.toolbar
import android.widget.MediaController

class VideoActivity : AppCompatActivity() {

    private var mCurrentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.tomergoldst.mediaapp.R.layout.activity_video)

        val title = intent.getStringExtra(EXTRA_TITLE)
        initToolbar(title)

        val url = intent.getStringExtra(EXTRA_URL)

        contentLoadingProgressBar.show()

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(SAVED_STATE_PLAYTIME);
        }

        url?.let {
            val mc = MediaController(this)
            videoView.setMediaController(mc)
            videoView.setVideoURI(Uri.parse(it))
            videoView.setOnPreparedListener {
                contentLoadingProgressBar.hide()
                if (mCurrentPosition > 0) {
                    videoView.seekTo(mCurrentPosition)
                }
                videoView.start()
            }
        } ?: throw RuntimeException("You must provide a url in the intent extra using the param 'EXTRA_URL'")

    }

    override fun onStart() {
        super.onStart()
        if (!videoView.isPlaying){
            videoView.start()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!videoView.isPlaying){
            videoView.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
    }

    override fun onStop() {
        super.onStop()
        videoView.stopPlayback()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVED_STATE_PLAYTIME, videoView.currentPosition)
    }

    private fun initToolbar(title: String?) {
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    companion object {
        const val EXTRA_URL = "EXTRA_URL"
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val SAVED_STATE_PLAYTIME = "SAVED_STATE_PLAYTIME"
    }

}
