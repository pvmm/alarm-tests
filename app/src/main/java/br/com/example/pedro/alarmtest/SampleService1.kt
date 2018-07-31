package br.com.example.pedro.alarmtest

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.util.Log

class SampleService1(name: String? = "Sample Service 1") : IntentService(name) {

    companion object {
        fun createIntent(bundle: Bundle? = null): Intent {
            MyApplication.warn("Creating SS1 intent...")
            return Intent(MyApplication.context.get(), this::class.java).apply {
                bundle?.let { this.putExtras(bundle) }
            }
        }
    }


    override fun onCreate() {
        super.onCreate()
        warn("SampleService1.onCreate() called successfully.")
    }


    override fun onHandleIntent(intent: Intent?) {
        Log.d("KOTLIN", "SampleService1.OnHandleIntent() called.")
        warn("SampleService1.onHandleIntent() called successfully with $intent.")

        intent?.let {
            val bundle = intent.extras
            warn("extras = $bundle")
        }
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        warn("SampleService1.onStartCommand() called successfully with $intent.")
        return START_STICKY
    }

}
