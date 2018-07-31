package br.com.example.pedro.alarmtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager


    private fun loadRequestCode(): Int {
        val requestCode = editText1.text.toString().toInt()
        warn("from editText value is $requestCode")
        return requestCode
    }


    private fun loadServiceType(): Int {
        val serviceType = if (switch1.isChecked) 1 else 0
        warn("from switch1 value is $serviceType")
        return serviceType
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(MainActivity::javaClass.name, "onCreate()")
        alarmManager = AlarmManager(this)

        addButton.setOnClickListener {
            val calendar = Calendar.getInstance().apply { add(Calendar.MINUTE, 5) }
            val serviceType = loadServiceType()
            val requestCode = loadRequestCode()

            alarmManager.scheduleAlarm(serviceType, requestCode, calendar.time)
            warn("Creating alarm $requestCode")
            editText1.text.clear()
        }

        removeButton.setOnClickListener {
            val serviceType = loadServiceType()
            val requestCode = loadRequestCode()

            if (!alarmManager.cancelAlarm(serviceType, requestCode)) {
                warn("Alarm $requestCode not found.")
            } else {
                warn("Canceling alarm $requestCode successful.")
            }
            editText1.text.clear()
        }

    }

}
