package com.eemmez.notification

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.onesignal.OSNotification
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationReceivedHandler @Inject constructor() {
    private val notificationLiveData = MutableLiveData<String>()
    private var observer: Observer<String>? = null

    internal fun setAdditionalData(notification: OSNotification) {
        notificationLiveData.postValue(notification.additionalData?.toString())
    }

    fun subscribe(
        lifecycleOwner: LifecycleOwner,
        onDataReceivedListener: (additionalData: String?) -> Unit
    ) {
        observer = Observer<String> { additionalData ->
            onDataReceivedListener.invoke(additionalData ?: "")
        }.also {
            notificationLiveData.observe(lifecycleOwner, it)
        }
    }

    fun unsubscribe(lifecycleOwner: LifecycleOwner) {
        this.notificationLiveData.removeObservers(lifecycleOwner)
        observer = null
    }
}