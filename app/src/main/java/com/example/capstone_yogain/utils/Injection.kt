package com.example.capstone_yogain.utils

import com.example.capstone_yogain.data.api.ApiConfig
import com.example.capstone_yogain.data.repo.YogaRepo

object Injection {
    fun provideRepository(token:String) : YogaRepo{
        val apiService = ApiConfig.getApiService()
        return YogaRepo(apiService, token)
    }
}