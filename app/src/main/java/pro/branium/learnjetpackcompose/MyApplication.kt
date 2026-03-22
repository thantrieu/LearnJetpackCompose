package pro.branium.learnjetpackcompose

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        FirebaseApp.initializeApp(this)
//        FacebookSdk.sdkInitialize(applicationContext)
//        AppEventsLogger.activateApp(this)
    }
}