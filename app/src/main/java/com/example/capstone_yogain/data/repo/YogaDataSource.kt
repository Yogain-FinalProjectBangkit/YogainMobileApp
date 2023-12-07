package com.example.capstone_yogain.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.capstone_yogain.data.api.ApiService
import com.example.capstone_yogain.data.response.ListYogaItem

class YogaDataSource(private val apiService: ApiService, private val token : String) : PagingSource<Int, ListYogaItem>() {
    override fun getRefreshKey(state: PagingState<Int, ListYogaItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListYogaItem> {
        return try{
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getStory(token, params.loadSize, position)

            LoadResult.Page(
                data = responseData.listStory,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.listStory.isEmpty()) null else position + 1
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }

    companion object{
        const val INITIAL_PAGE_INDEX = 1
    }

}