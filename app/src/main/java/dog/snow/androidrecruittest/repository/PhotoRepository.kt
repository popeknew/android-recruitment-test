package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.service.PhotoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PhotoRepository(private val service: PhotoService) {

    suspend fun getPhotos(): Response<List<RawPhoto>> = withContext(Dispatchers.IO) {
        service.getPhotos()
    }
}