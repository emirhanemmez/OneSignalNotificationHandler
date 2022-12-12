package com.eemmez.notification

import android.content.Context
import com.eemmez.notification.di.NotificationEntryPoint
import com.onesignal.OSNotification
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal
import dagger.hilt.EntryPoints

@Suppress("unused")
class NotificationBackgroundDataReceiver : OneSignal.OSRemoteNotificationReceivedHandler {
    override fun remoteNotificationReceived(
        context: Context, notificationReceivedEvent: OSNotificationReceivedEvent
    ) {
        val notification: OSNotification = notificationReceivedEvent.notification

        val notificationReceivedHandler = EntryPoints.get(context, NotificationEntryPoint::class.java).notificationReceivedHandler()


        notificationReceivedHandler.setAdditionalData(notification)

        notificationReceivedEvent.complete(notification)
    }
}