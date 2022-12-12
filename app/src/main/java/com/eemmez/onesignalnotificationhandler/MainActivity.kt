package com.eemmez.onesignalnotificationhandler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eemmez.notification.NotificationOpenedHandler
import com.eemmez.notification.NotificationReceivedHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var notificationOpenedHandler: NotificationOpenedHandler

    @Inject
    lateinit var notificationReceivedHandler: NotificationReceivedHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationReceivedHandler.subscribe(this) {
            Toast.makeText(this, "Notification received with additional data: $it", Toast.LENGTH_SHORT).show()
        }

        notificationOpenedHandler.subscribe(this) {
            Toast.makeText(this, "Notification opened with additional data: $it", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        notificationReceivedHandler.unsubscribe(this)
        notificationOpenedHandler.unsubscribe(this)
    }
}