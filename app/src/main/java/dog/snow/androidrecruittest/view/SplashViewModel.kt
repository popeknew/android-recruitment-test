package dog.snow.androidrecruittest.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import kotlinx.coroutines.launch

class SplashViewModel(
    private val photoRepository: PhotoRepository,
    private val albumRepository: AlbumRepository
) : ViewModel() {

    private val _allPhotos = MutableLiveData<List<RawPhoto>>()
    val allPhotos: LiveData<List<RawPhoto>> = _allPhotos

    private val _allAlbums = MutableLiveData<List<RawAlbum>>()
    val allAlbums: LiveData<List<RawAlbum>> = _allAlbums

    init {
        viewModelScope.launch {
            val photosResponse = photoRepository.getPhotos()
            if (photosResponse.isSuccessful) {
                _allPhotos.value = photosResponse.body()
            }
          //  val albumsResponse = albumRepository.getAllAlbums()
        }
    }
}