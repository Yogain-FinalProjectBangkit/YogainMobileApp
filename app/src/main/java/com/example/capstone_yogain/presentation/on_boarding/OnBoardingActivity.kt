package com.example.capstone_yogain.presentation.on_boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone_yogain.databinding.ActivityOnBoardingBinding
import com.example.capstone_yogain.presentation.login.LoginActivity

class OnBoardingActivity : AppCompatActivity() {

    private var _binding : ActivityOnBoardingBinding?= null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)



        binding?.startWithUs?.setOnClickListener {
            val toLogin = Intent(this@OnBoardingActivity, LoginActivity::class.java)
            startActivity(toLogin)
        }
    }
}