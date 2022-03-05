package com.droidli.brod

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    private val localBroadcastManager by lazy{
        LocalBroadcastManager.getInstance(this)
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            if(intent?.action == "loaded") {
                val percent = intent.getIntExtra("percent", 0)
                progressBar.progress = percent
            }
        }

    }
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar2)
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(MyReceiver.ACTION_CLICKED)
            intent.putExtra(MyReceiver.EXTRA_COUNT, count++)
            localBroadcastManager.sendBroadcast(intent)
        }
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(MyReceiver.ACTION_CLICKED)
            addAction("loaded")
        }
        localBroadcastManager.registerReceiver(receiver, intentFilter)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(receiver)
    }

}