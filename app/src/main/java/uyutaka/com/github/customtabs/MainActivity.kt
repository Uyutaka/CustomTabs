package uyutaka.com.github.customtabs

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent

class MainActivity : AppCompatActivity() {
    val url = "https://dev.to/"

    @SuppressLint("RemoteViewLayout")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var defaultBtn = findViewById<Button>(R.id.default_custom_tabs)
        defaultBtn.setOnClickListener {
            var builder = CustomTabsIntent.Builder()

            var customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

        var titleBtn = findViewById<Button>(R.id.title_custom_tabs)
        titleBtn.setOnClickListener {
            var builder = CustomTabsIntent.Builder()
            builder.setShowTitle(true)
            var customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

        var urlBarBtn = findViewById<Button>(R.id.url_bar_hiding_custom_tabs)
        urlBarBtn.setOnClickListener {
            var builder = CustomTabsIntent.Builder()
            builder.setUrlBarHidingEnabled(false)
            var customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

        var secondaryBtn = findViewById<Button>(R.id.secondary_toolbar_custom_tabs)
        secondaryBtn.setOnClickListener {
            var builder = CustomTabsIntent.Builder()
            var remoteViews = RemoteViews(this.packageName, R.layout.remote_view)
            builder.setSecondaryToolbarViews(remoteViews, null, null)
            var customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

        var menuBtn = findViewById<Button>(R.id.menu_custom_tabs)
        menuBtn.setOnClickListener {
            var builder = CustomTabsIntent.Builder()

            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "msg")
                putExtra(AlarmClock.EXTRA_HOUR, 3)
                putExtra(AlarmClock.EXTRA_MINUTES, 0)
            }
            val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                3,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
            builder.addMenuItem("Added by addMenuItem()", pendingIntent)

            var customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }
    }
}