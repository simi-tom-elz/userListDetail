package com.example.test_xpayback.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import com.example.test_xpayback.apicall.UserRepository
import com.example.test_xpayback.model.User

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {

    val users: Flow<PagingData<User>> = userRepository.getUsers().cachedIn(viewModelScope)
}
