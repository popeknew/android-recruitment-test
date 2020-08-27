package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawPhoto
import retrofit2.Response
import retrofit2.http.GET

interface PhotoService {

    @GET(value = "/photos")
    suspend fun getPhotos(): Response<List<RawPhoto>>
}