package com.example.capstone_yogain.presentation.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.capstone_yogain.R
import com.example.capstone_yogain.data.pref.AuthViewModel
import com.example.capstone_yogain.data.pref.SessionManager
import com.example.capstone_yogain.databinding.ActivityRegisterBinding
import com.example.capstone_yogain.presentation.login.LoginActivity
import com.example.capstone_yogain.utils.showToast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private var _binding : ActivityRegisterBinding ?= null
    private val binding get() = _binding
    private lateinit var auth : FirebaseAuth
    private lateinit var sessionManager: SessionManager
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()
        sessionManager = SessionManager(this)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        showLoading(false)

        binding?.btnRegister?.setOnClickListener {
            showLoading(true)
            binding?.apply {
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                authViewModel.register(name, email, password,
                    onSuccess = {
                        showLoading(false)
                        showToast(getString(R.string.register_success))
                        val toLogin = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(toLogin)
                        finishAffinity()
                    },
                    onError = { error ->
                        showLoading(false)
                        showToast(error)
                    })

            }
        }

        binding?.tvToLogin?.setOnClickListener {
            val loginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(loginIntent)
            finishAffinity()
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}