package com.gumibom.travelmaker.service

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gumibom.travelmaker.R
import com.gumibom.travelmaker.ui.main.MainActivity

private const val TAG = "MyFirebaseMessageService_싸피"
class MyFirebaseMessageService : FirebaseMessagingService() {

    // 새로운 토큰이 생성될 때 마다 해당 콜백이 호출된다.
        override fun onNewToken(token: String) {
            super.onNewToken(token)
            Log.d(TAG, "onNewToken: $token")
            // TODO 새로운 토큰 수신 시 서버로 전송

        }

        // Foreground, Background 모두 처리하기 위해서는 data에 값을 담아서 넘긴다.
        //https://firebase.google.com/docs/cloud-messaging/android/receive
        override fun onMessageReceived(remoteMessage: RemoteMessage) {
            var messageTitle = ""
            var messageContent = ""

            if(remoteMessage.notification != null){ // notification이 있는 경우 foreground처리
                //foreground
                messageTitle= remoteMessage.notification!!.title.toString()
                messageContent = remoteMessage.notification!!.body.toString()

                Log.d(TAG, "onMessageReceived: FCM 확인")

            }else{  // background 에 있을경우 혹은 foreground에 있을경우 두 경우 모두
                Log.d(TAG, "onMessageReceived: FCM 확인")

                var data = remoteMessage.data
                Log.d(TAG, "data.message: $data")
                Log.d(TAG, "data.message: ${data["title"]}")
                Log.d(TAG, "data.message: ${data["body"]}")

                messageTitle = data["title"].toString()
                messageContent = data["body"].toString()
            }

            val mainIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            val mainPendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_IMMUTABLE)

            val builder1 = NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setContentTitle(messageTitle)
                .setContentText(messageContent)
                .setAutoCancel(true)
                .setContentIntent(mainPendingIntent)

            NotificationManagerCompat.from(applicationContext).apply {
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notify(101, builder1.build())
            }
    }

}