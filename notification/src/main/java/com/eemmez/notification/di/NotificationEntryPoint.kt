package com.eemmez.notification.di

import com.eemmez.notification.NotificationReceivedHandler
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NotificationEntryPoint {
    fun notificationReceivedHandler(): NotificationReceivedHandler
}