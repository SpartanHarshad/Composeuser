package io.harshad.compuser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.harshad.compuser.data.local.UserEntity
import io.harshad.compuser.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val uRepository: UserRepository) : ViewModel() {

    private val _userList = MutableStateFlow<List<UserEntity>>(emptyList())
    val userItems: Flow<List<UserEntity>> get() = _userList

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            uRepository.getUserList()
            uRepository.userItems.collect { uItems ->
                _userList.value = uItems
            }
        }
    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            uRepository.insertUser(user)
        }
    }

}