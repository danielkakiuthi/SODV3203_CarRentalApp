package com.example.sodv3203_carrentalapp.data

import kotlinx.coroutines.flow.Flow

class UserRepositoryOffline(private val userDao: UserDao) : UserRepository {

    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()
    override fun getUserStream(id: Int): Flow<User?> = userDao.getUser(id)
    override suspend fun insertUser(user: User) = userDao.insert(user)
    override suspend fun deleteUser(user: User) = userDao.delete(user)
    override suspend fun updateUser(user: User) = userDao.update(user)

}