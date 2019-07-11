package com.tomergoldst.mediaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.tomergoldst.mediaapp.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.lang.RuntimeException


class WebViewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val title = intent.getStringExtra(EXTRA_TITLE)
        initToolbar(title)

        val url = intent.getStringExtra(EXTRA_URL)
        url?.let {
            webView.loadUrl(url)
        } ?: throw RuntimeException("You must provide a url in the intent extra using the param 'EXTRA_URL'")

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
    }

}
