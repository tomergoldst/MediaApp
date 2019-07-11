package com.tomergoldst.mediaapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomergoldst.mediaapp.ui.LinkEntryViewHolder
import com.tomergoldst.mediaapp.ui.MainActivity
import com.tomergoldst.mediaapp.ui.WebViewActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class OpenLinkTest {

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun whenClickOnEntryOfTypeLink_goesToWebViewActivity() {
        // todo: delaying until resource is ready should be done properly with idling resource
        Thread.sleep(3000)

        onView(withId(R.id.fragmentMainRecyclerView))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<LinkEntryViewHolder>(0, click()))

        Thread.sleep(1000)

        intended(hasComponent(WebViewActivity::class.java.name))

    }
}

