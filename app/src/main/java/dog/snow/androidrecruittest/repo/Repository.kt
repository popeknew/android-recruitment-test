package dog.snow.androidrecruittest.repo

import dog.snow.androidrecruittest.model.RawPhoto
import dog.snow.androidrecruittest.net.RestApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Repository(private val service: RestApiService) {

    suspend fun getPhotos(): Response<List<RawPhoto>> = withContext(Dispatchers.IO) {
        service.getPhotos()
    }

    suspend fun getUserById(userId: Int) = withContext(Dispatchers.IO) {
        service.getUserById(userId)
    }

    suspend fun getAlbumById(albumId: Int) = withContext(Dispatchers.IO) {
        service.getAlbumById(albumId)
    }
}