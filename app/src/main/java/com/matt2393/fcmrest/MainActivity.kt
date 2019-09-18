package com.matt2393.fcmrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.matt2393.fcmrest.Entitys.Notif
import com.matt2393.fcmrest.Entitys.NotifData
import com.matt2393.fcmrest.Rest.RetrofitSingleton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic("gdg")



        enviar.setOnClickListener{
            var notD= NotifData(title_notif.text.toString(),desc_notif.text.toString())
            var notif=Notif("/topic/gdg",notD)
            RetrofitSingleton.getInstance(this)
                .addRequest(
                    RetrofitSingleton.getInstance(this)
                        .requests.postFCM(notif)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {res->
                                Log.e("FCM","exito: ${res.string()}")
                            },
                            {e ->
                                Log.e("FCM", "error: $e")
                            }
                        )
                )
        }

    }
}
