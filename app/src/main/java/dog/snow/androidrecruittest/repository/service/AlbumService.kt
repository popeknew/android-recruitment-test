package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET(value = "/albums/{id}")
    suspend fun getAlbumById(@Path("id") albumId: Int): Response<RawAlbum>
}