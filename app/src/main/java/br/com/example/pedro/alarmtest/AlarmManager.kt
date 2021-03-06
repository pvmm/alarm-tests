package br.com.example.pedro.alarmtest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import br.com.example.pedro.alarmtest.MyApplication.Companion.warn
import java.util.*

class AlarmManager(private val context: Context) {

    companion object {
        val extraTimestampKey = "timestamp"
    }

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as android.app.AlarmManager

    private fun alarmExists(serviceType: Int, requestCode: Int): Boolean {
        val intent = if (serviceType == 0) SampleService1.createIntent(context) else SampleService2.createIntent(context)
        return PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_NO_CREATE) != null
    }


    private fun createIntent(serviceType: Int, bundle: Bundle? = null): Intent {
        return if (serviceType == 0) SampleService1.createIntent(context, bundle) else SampleService2.createIntent(context, bundle)
    }


    private fun createPendingIntent(serviceType: Int, requestCode: Int, intent: Intent? = null): PendingIntent? {
        intent?.let {
            return PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        return PendingIntent.getService(context, requestCode, createIntent(serviceType),
                PendingIntent.FLAG_UPDATE_CURRENT)
    }


    fun scheduleAlarm(serviceType: Int, requestCode: Int, alarmDate: Date) {
        Log.d("KOTLIN", "AlarmManager.scheduleAlarm() called with timestamp = ${alarmDate.time}")
        val intent = createIntent(serviceType, Bundle().apply { putLong(extraTimestampKey, alarmDate.time) })
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmDate.time,
                DateUtils.WEEK_IN_MILLIS, createPendingIntent(serviceType, requestCode, intent))
        warn("Alarm set to launch at $alarmDate")
    }


    fun cancelAlarm(serviceType: Int, requestCode: Int): Boolean {
        when (alarmExists(serviceType, requestCode)) {
            true -> PendingIntent.getService(context, requestCode, createIntent(serviceType), 0)
                    .let { alarmManager.cancel(it); return true }
            else -> return false
        }
    }

}