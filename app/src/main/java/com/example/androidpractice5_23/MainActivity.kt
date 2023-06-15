package com.example.androidpractice5_23

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.os.HandlerCompat
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.example.androidpractice5_23.ui.theme.AndroidPractice5_23Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    private lateinit var timeTextView: TextView

    private val updateInterval: Long = 1000
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            updateTime()
            timeTextView.postDelayed(this, updateInterval)
        }
    }
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* setContent {
            AndroidPractice5_23Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }*/
        setContentView(R.layout.activity_time)

        timeTextView = findViewById(R.id.timeTextView)


    }
    override fun onResume() {
        super.onResume()
        timeTextView.postDelayed(updateTimeRunnable, updateInterval)
    }
    override fun onDestroy() {
        super.onDestroy()
        // Remove any pending Runnable when the activity is destroyed
        timeTextView.removeCallbacks(::updateTime)
    }
    override fun onPause() {
        super.onPause()
        timeTextView.removeCallbacks(updateTimeRunnable)
    }
    private fun updateTime() {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime = sdf.format(Date())
        timeTextView.text = currentTime

        // Schedule the next update after the specified interval
        timeTextView.postDelayed(::updateTime, updateInterval)    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}*/

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPractice5_23Theme {
        Greeting("Android")
    }
}*/
