package com.example.capstone_yogain.data.response

import com.google.gson.annotations.SerializedName

class RegisterResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)