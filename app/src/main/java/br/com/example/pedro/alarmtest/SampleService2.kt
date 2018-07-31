package br.com.example.pedro.alarmtest

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class SampleService2(name: String? = "SampleService2") : IntentService(name) {

    companion object {
        fun createIntent(context: Context, bundle: Bundle? = null): Intent {
            MyApplication.warn("Creating SS2 intent...")
            return Intent(context, SampleService2::class.java).apply {
                bundle?.let { bundle -> putExtras(bundle) }
            }
        }
    }


    override fun onCreate() {
        super.onCreate()
        warn("SampleService2.onCreate() called successfully.")
    }


    override fun onHandleIntent(intent: Intent?) {
        Log.d("KOTLIN", "SampleService2.onHandleIntent() called.")
        warn("SampleService2.onHandleIntent() called successfully with $intent.")

        intent?.let {
            val bundle = intent.extras
            warn("extras = $bundle")
        }
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        warn("SampleService2.onStartCommand() called successfully with $intent.")
        return START_STICKY
    }

}
