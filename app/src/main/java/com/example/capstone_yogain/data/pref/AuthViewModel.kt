package com.example.capstone_yogain.data.pref

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.capstone_yogain.R
import com.example.capstone_yogain.data.api.ApiConfig
import com.example.capstone_yogain.data.response.LoginResponse
import com.example.capstone_yogain.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel (application: Application) : AndroidViewModel(application){
    fun login(email : String, password : String, onSuccess : (String, String) -> Unit, onError : (String) -> Unit) {
        ApiConfig.getApiService().login(email, password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (password.isNotEmpty() && email.isNotEmpty()){
                        when(response.code()){
                            200 -> {
                                val token = response.body()?.loginResult!!.token
                                val name = response.body()?.loginResult!!.name
                                onSuccess(token, name)
                            }
                            else -> {
                                Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                                onError(getApplication<Application>().getString(R.string.error))
                            }
                        }
                    }else if (email.isEmpty()){
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        onError(getApplication<Application>().getString(R.string.empty_email))
                    }else if (password.isEmpty()){
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        onError(getApplication<Application>().getString(R.string.empty_password))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                    onError(getApplication<Application>().getString(R.string.error))
                }

            })
    }

    fun register(name : String, email : String, password : String, onSuccess : () -> Unit, onError : (String) -> Unit) {
        ApiConfig.getApiService().register(name, email, password)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                        when(response.code()){
                            201 -> {
                                onSuccess()
                            }
                            400 -> {
                                Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                                onError(getApplication<Application>().getString(R.string.email_have_been_register))
                            }else -> {
                            Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        }
                        }
                    }else if (name.isEmpty()){
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        onError(getApplication<Application>().getString(R.string.empty_name))
                    }else if(email.isEmpty()){
                        (response.message())
                        onError(getApplication<Application>().getString(R.string.empty_email))
                    }else if (password.isEmpty()){
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        onError(getApplication<Application>().getString(R.string.empty_password))
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                    onError(getApplication<Application>().getString(R.string.error))
                }

            })
    }
}
