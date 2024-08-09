package com.example.test_xpayback.fragment

import com.example.test_xpayback.apicall.UserRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_xpayback.viewmodel.UserDetailViewModel
import com.example.test_xpayback.viewmodel.UserListViewModel

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ")
    }
}

class UserDetailViewModelFactory(private val repository: UserRepository, private val userId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserDetailViewModel(repository, userId) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}
