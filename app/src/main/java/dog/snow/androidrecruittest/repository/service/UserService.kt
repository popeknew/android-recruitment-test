package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET(value = "/users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<RawUser>
}