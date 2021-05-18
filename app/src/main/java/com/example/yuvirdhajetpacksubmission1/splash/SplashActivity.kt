package com.example.yuvirdhajetpacksubmission1.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.yuvirdhajetpacksubmission1.HomeActivity
import com.example.yuvirdhajetpacksubmission1.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}