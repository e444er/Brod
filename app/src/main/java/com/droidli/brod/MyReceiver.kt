package com.droidli.brod

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                Toast.makeText(p0, "ACTION_AIRPLANE_MODE_CHANGED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}