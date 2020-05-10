package com.example.pomoapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
        // use val instead of var since it will be immutable then
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_timer_icon)
                .setContentTitle("Timer is Complete")
                .setContentText("take a 5-minute break, you deserve it:) resume the timer after you're done!")
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("take a 5-minute break, you deserve it:) resume the timer after you're done!"))
                // set priority to HIGH so that notification will be displayed at top of screen
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                // set the intent that will fire when the user taps the notification
                .setContentIntent(PendingIntent.getActivity(this, 0, intent, 0))
                .setAutoCancel(true)    // notification is removed when tapped by user
                .setVibrate(longArrayOf(1L, 2L, 3L))    // add vibration to notification
                .build()
        notificationManager!!.notify(1, notification)
    }

    // Create notification channel, including setting priority of notification
    private fun createNotificationChannels() {
        // if statement that checks to make sure OS is API 26 or greater
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notifChannel1 = NotificationChannel(
                    CHANNEL_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            notifChannel1.description = "This is Channel 1"
            val notificationManager:NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notifChannel1)
        }
    }

    companion object {
        // 25 minutes to milliseconds
        private const val START_TIME_IN_MILL: Long = 18000
    }
}