package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val service: UserService) {

    suspend fun getUserById(userId: Int) = withContext(Dispatchers.IO) {
        service.getUserById(userId)
    }
}