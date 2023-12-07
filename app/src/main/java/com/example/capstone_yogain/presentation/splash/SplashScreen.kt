package com.example.capstone_yogain.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.capstone_yogain.data.pref.SessionManager
import com.example.capstone_yogain.databinding.ActivitySplashScreenBinding
import com.example.capstone_yogain.presentation.main.MainActivity
import com.example.capstone_yogain.presentation.on_boarding.OnBoardingActivity
import com.example.capstone_yogain.utils.ConstVal.duration
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private var _binding : ActivitySplashScreenBinding ?= null
    private val binding get() = _binding

    private lateinit var sessionManager: SessionManager
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        FirebaseAuth.getInstance().currentUser

        sessionManager = SessionManager(this)
        val isLogin = sessionManager.isLogin
        val user = auth.currentUser

        Handler(Looper.getMainLooper()).postDelayed({
            when{
                isLogin -> {
                    val login = Intent(this, MainActivity::class.java)
                    startActivity(login)
                    finishAffinity()
                }
                user == null -> {
                    val onBoard = Intent(this@SplashScreen, OnBoardingActivity::class.java)
                    startActivity(onBoard)
                    finishAffinity()
                }
            }
        }, duration)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}