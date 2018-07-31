package br.com.example.pedro.alarmtest

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.warn(message: String) {
    Log.d("[KOTLIN]", "${this::class.simpleName}: $message")
    Toast.makeText(MyApplication.context.get(), message, Toast.LENGTH_SHORT).show()
}