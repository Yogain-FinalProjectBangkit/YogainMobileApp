package com.example.capstone_yogain.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.capstone_yogain.data.api.ApiService
import com.example.capstone_yogain.data.response.ListYogaItem

class YogaRepo(private val apiService: ApiService, private val token : String) {
    fun getYoga() : LiveData<PagingData<ListYogaItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory ={
                YogaDataSource(apiService, token)
            }
        ).liveData
    }
}