package com.eemmez.onesignalnotificationhandler

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.eemmez.notification.NotificationOpenedHandler
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var notificationOpenedHandler: NotificationOpenedHandler

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()

        setupOneSignal()
    }

    private fun setupOneSignal() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)

        OneSignal.setNotificationOpenedHandler {
            handler.postDelayed({
                notificationOpenedHandler.setAdditionalData(it.notification)
            }, 1000)
        }
        OneSignal.setAppId("your_app_id")
    }
}