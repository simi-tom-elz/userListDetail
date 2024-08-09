package com.example.test_xpayback.apicall


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test_xpayback.model.User

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getUsers(params.loadSize, (currentPage - 1) * params.loadSize)

            run {
                val data = response.users

                LoadResult.Page(
                    data = data,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (data.isEmpty()) null else currentPage + 1
                )
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
