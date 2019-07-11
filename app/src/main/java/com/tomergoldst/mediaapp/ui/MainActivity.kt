package com.tomergoldst.mediaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tomergoldst.mediaapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        val mainFragment = MainFragment.newInstance()

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, mainFragment).commit()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.title = getString(R.string.app_name)
    }
}
