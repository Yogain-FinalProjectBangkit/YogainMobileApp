package com.example.capstone_yogain.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone_yogain.presentation.main.MainViewModel
import com.example.capstone_yogain.utils.Injection

class ViewModelFactory(private val token : String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Injection.provideRepository("bearer $token")) as T
        }
        throw IllegalArgumentException("error ${modelClass.name}")
    }
}
