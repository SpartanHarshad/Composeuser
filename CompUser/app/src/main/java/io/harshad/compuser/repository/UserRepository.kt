package io.harshad.compuser.repository

import io.harshad.compuser.data.local.UserDao
import io.harshad.compuser.data.local.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    private val _userList = MutableStateFlow<List<UserEntity>>(emptyList())
    val userItems: Flow<List<UserEntity>> get() = _userList

    suspend fun insertUser(user: UserEntity) {
         userDao.insertUser(user)
    }

    suspend fun getUserList(){
       val users = userDao.getUsers()
        _userList.emit(users)
    }

}