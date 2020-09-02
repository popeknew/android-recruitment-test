package dog.snow.androidrecruittest.net

import dog.snow.androidrecruittest.constants.PHOTO_LIMIT
import dog.snow.androidrecruittest.model.RawAlbum
import dog.snow.androidrecruittest.model.RawPhoto
import dog.snow.androidrecruittest.model.RawUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiService {

    @GET(value = "/photos?_limit=$PHOTO_LIMIT")
    suspend fun getPhotos(): Response<List<RawPhoto>>

    @GET(value = "/albums/{id}")
    suspend fun getAlbumById(@Path("id") albumId: Int): Response<RawAlbum>

    @GET(value = "/users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<RawUser>
}