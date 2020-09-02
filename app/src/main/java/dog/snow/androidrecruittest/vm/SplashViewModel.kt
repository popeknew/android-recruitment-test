package dog.snow.androidrecruittest.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dog.snow.androidrecruittest.repo.Repository
import dog.snow.androidrecruittest.model.RawAlbum
import dog.snow.androidrecruittest.model.RawPhoto
import dog.snow.androidrecruittest.model.RawUser
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _allPhotos = MutableLiveData<List<RawPhoto>>()
    val allPhotos: LiveData<List<RawPhoto>> = _allPhotos

    private val _allAlbums = MutableLiveData<List<RawAlbum>>()
    val allAlbums: LiveData<List<RawAlbum>> = _allAlbums

    private val _allUsers = MutableLiveData<List<RawUser>>()
    val allUsers: LiveData<List<RawUser>> = _allUsers

    fun getAllPhotos() = viewModelScope.launch {
        val photosResponse = repository.getPhotos()
        if (photosResponse.isSuccessful) {
            _allPhotos.value = photosResponse.body()
        }
    }

    private fun getAlbumIds(photos: List<RawPhoto>): Set<Int> {
        val albumIdSet = mutableSetOf<Int>()
        photos.forEach { photo ->
            albumIdSet.add(photo.albumId)
        }

        return albumIdSet
    }

    fun getAlbums(photos: List<RawPhoto>) {
        val albumIds = getAlbumIds(photos)
        viewModelScope.launch {
            val albums = mutableListOf<RawAlbum>()
            albumIds.forEach { albumId ->
                val albumResponse = repository.getAlbumById(albumId)
                if (albumResponse.isSuccessful) {
                    albums.add(albumResponse.body()!!)
                }
            }
            _allAlbums.value = albums.toList()
        }
    }

    private fun getUserIds(albums: List<RawAlbum>): Set<Int> {
        val userIdSet = mutableSetOf<Int>()
        albums.forEach { album ->
            userIdSet.add(album.userId)
        }

        return userIdSet
    }

    fun getUsers(albums: List<RawAlbum>) {
        val userIds = getUserIds(albums)
        viewModelScope.launch {
            val users = mutableListOf<RawUser>()
            userIds.forEach { userId ->
                val userResponse = repository.getUserById(userId)
                if (userResponse.isSuccessful) {
                    users.add(userResponse.body()!!)
                }
            }
            _allUsers.value = users.toList()
        }
    }
}