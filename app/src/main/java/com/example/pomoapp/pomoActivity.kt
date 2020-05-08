package com.example.pomoapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class pomoActivity : AppCompatActivity() {
    private val CHANNEL_ID = "Channel1"
    private val NOTIFICATION_ID = 1
    private var textViewCountdown: TextView? = null
    private var buttonStartPause: Button? = null
    private var buttonReset: Button? = null
    private var countDownTImer: CountDownTimer? = null
    private var timerRunning = false
    private var timeLeftInMill = START_TIME_IN_MILL
    private var notificationManager: NotificationManagerCompat? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pomo_activity)
        buttonStartPause = findViewById(R.id.startPomo)
        buttonReset = findViewById(R.id.resetPomo)
        textViewCountdown = findViewById(R.id.text_view_countdown)
        val buttonStartPause = buttonStartPause
        val buttonReset = buttonReset
        buttonStartPause?.setOnClickListener(View.OnClickListener {
            if (timerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        })

        buttonReset?.setOnClickListener(View.OnClickListener { resetTimer() })
        updateCountDownText()
        notificationManager = NotificationManagerCompat.from(this)
        createNotificationChannels()
    }

    private fun startTimer() {
        countDownTImer = object : CountDownTimer(timeLeftInMill, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMill = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                timerRunning = false
                buttonReset!!.text = "Reset"
                buttonStartPause!!.text = "Start"
                // set up notification here.
                sendNotificationChannel()
            }
        }.start()
        timerRunning = true
        buttonStartPause!!.text = "Pause"
    }

    private fun pauseTimer() {
        countDownTImer!!.cancel()
        timerRunning = false
        buttonStartPause!!.text = "Resume"
    }

    private fun resetTimer() {
        timeLeftInMill = START_TIME_IN_MILL
        updateCountDownText()
    }

    private fun updateCountDownText() {
        val minute = (timeLeftInMill / 1000).toInt() / 60
        val seconds = (timeLeftInMill / 1000).toInt() % 60
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minute, seconds)
        textViewCountdown!!.text = timeLeftFormatted
    }

    private fun sendNotificationChannel() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_timer_notification)
                .setContentTitle("Timer is complete")
                .setContentText("take a quick break, you deserve it!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build()
        notificationManager!!.notify(1, notification)
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                    CHANNEL_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            channel1.description = "this is channel 1"
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel1)
        }
    }

    companion object {
        // 25 minutes to milliseconds
        private const val START_TIME_IN_MILL: Long = 1800000
    }
}