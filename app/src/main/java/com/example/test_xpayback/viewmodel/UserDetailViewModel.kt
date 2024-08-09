package com.example.test_xpayback.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_xpayback.apicall.UserRepository
import com.example.test_xpayback.model.User
import kotlinx.coroutines.launch

class UserDetailViewModel(private val repository: UserRepository, userId: Int) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchUserById(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = repository.getUserById(id)
                _user.value = response
            } catch (e: Exception) {

            } finally {
                _loading.value = false
            }
        }
    }
}
