package br.com.example.pedro.alarmtest

import android.app.Application
import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

class MyApplication : Application() {

    companion object {
        lateinit var context: WeakReference<Context>

        fun warn(message: String) {
            context.get()?.warn(message)
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(this.applicationContext)
        warn("Loading app... $applicationContext")
    }

}