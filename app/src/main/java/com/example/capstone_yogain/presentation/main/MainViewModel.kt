package com.example.capstone_yogain.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.capstone_yogain.data.repo.YogaRepo
import com.example.capstone_yogain.data.response.ListYogaItem

class MainViewModel(repository: YogaRepo) : ViewModel(){
    val yoga : LiveData<PagingData<ListYogaItem>> = repository.getYoga().cachedIn(viewModelScope)
}