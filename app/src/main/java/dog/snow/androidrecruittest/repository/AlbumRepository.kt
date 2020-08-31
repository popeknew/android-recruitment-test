package dog.snow.androidrecruittest.repository

import dog.snow.androidrecruittest.repository.service.AlbumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepository(private val service: AlbumService) {

    suspend fun getAlbumById(albumId: Int) = withContext(Dispatchers.IO) {
        service.getAlbumById(albumId)
    }
}