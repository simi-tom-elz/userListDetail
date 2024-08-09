package com.example.test_xpayback.apicall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.test_xpayback.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiService: ApiService) {

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }
    suspend fun getUserById(id: Int) = apiService.getUserDetail(id)

}
