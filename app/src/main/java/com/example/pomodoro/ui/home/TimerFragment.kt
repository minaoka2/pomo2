package com.example.pomodoro.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pomodoro.R

class TimerFragment : AppCompatActivity() {
    enum class TimerState {
        Paused, Running, Restart
    }
    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.Paused
    private var secondsRemaining: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_timer)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        // Get the count argument
        val count = args.myArg
        // Get the string and format it with the count
        val countText = getString(R.string.random_heading, count)
        // Set it for textview_header
        view.findViewById<TextView>(R.id.textview_header).text = countText
        // Get a random number between 0 and the count
        val random = java.util.Random()
        var randomNumber = 0
        if (count > 0) {
            randomNumber = random.nextInt(count + 1)
        }
        // Convert random number into a string and set it as text
        view.findViewById<TextView>(R.id.textview_random).text = randomNumber.toString()
    }
}