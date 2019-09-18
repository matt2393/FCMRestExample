package com.matt2393.fcmrest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(rm: RemoteMessage) {
        var tit:String = rm.data.get("tit")!!
        var desc:String = rm.data.get("desc")!!

        var notifMC=NotificationManagerCompat.from(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            var notifM: NotificationManager=  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var notCh : NotificationChannel = NotificationChannel("GDG","GDG",NotificationManager.IMPORTANCE_HIGH)

            notCh.enableVibration(true)
            notCh.description="Canal de prueba"

            notifM.createNotificationChannel(notCh)
        }

        var notif = NotificationCompat.Builder(this,"GDG")
        notif.setContentTitle(tit)
        notif.setContentText(desc)

        notifMC.notify(0,notif.build())


    }
}
