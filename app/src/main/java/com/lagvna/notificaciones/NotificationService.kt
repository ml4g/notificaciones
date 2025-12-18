package com.lagvna.notificaciones

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import kotlin.random.Random
import kotlin.random.nextInt

class NotificationService(private val context: Context) {

    private val notificationManager = context.getSystemService(
        NotificationManager::class.java)

    fun showBasicNotification(){

        val notification = NotificationCompat.Builder(
            context, "123"
        ).setContentTitle("Soy básica")
            .setContentText("Yo soy una notificación básica")
            .setSmallIcon(R.drawable.noti)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )

    }

    fun showLargeNotification() {

        val notification = NotificationCompat.Builder(
            context, "123"
        ).setContentTitle("Soy Grande")
            .setContentText("Yo soy una notificación grande")
            .setSmallIcon(R.drawable.noti)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dapibus ultricies dignissim. Pellentesque gravida risus sit amet dictum facilisis. Maecenas semper facilisis tellus, nec maximus neque accumsan vel. Duis fermentum velit condimentum, lacinia metus vel, malesuada odio. Praesent efficitur libero et dui egestas mollis. Praesent consectetur ultrices metus, non tempor felis ornare quis. Vivamus tristique ullamcorper velit, nec iaculis metus.")
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )

    }

    fun showInboxNotification(){
        val notification =
            NotificationCompat.Builder(context, "123")
                .setContentTitle("Inbox")
                .setContentText("Soy una notificación Inbox")
                .setSmallIcon(R.drawable.noti)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine("Linea 1")
                        .addLine("Linea 2")
                        .addLine("Maxi")
                )
                .setAutoCancel(true)
                .build()

        notificationManager.notify(
            Random.nextInt(),
            notification

        )

    }

}