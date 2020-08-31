package dog.snow.androidrecruittest.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.UserRepository
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.ui.model.ListItem
import kotlinx.coroutines.launch

class SplashViewModel(
    private val photoRepository: PhotoRepository,
    private val albumRepository: AlbumRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _allPhotos = MutableLiveData<List<RawPhoto>>()
    val allPhotos: LiveData<List<RawPhoto>> = _allPhotos

    private val _allAlbums = MutableLiveData<List<RawAlbum>>()
    val allAlbums: LiveData<List<RawAlbum>> = _allAlbums

    private val _allUsers = MutableLiveData<List<RawUser>>()
    val allUsers: LiveData<List<RawUser>> = _allUsers

    private val mockList = MutableLiveData<MutableList<ListItem>>()

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
                val albumResponse = albumRepository.getAlbumById(albumId)
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
                val userResponse = userRepository.getUserById(userId)
                if (userResponse.isSuccessful) {
                    users.add(userResponse.body()!!)
                }
            }
            _allUsers.value = users.toList()
        }
    }

    init {
        viewModelScope.launch {
            val photosResponse = photoRepository.getPhotos()
            if (photosResponse.isSuccessful) {
                _allPhotos.value = photosResponse.body()
            }

/*            val firstElement = allPhotos.value?.first()
            if (firstElement != null) {
                val listitem = ListItem(
                    firstElement.id,
                    firstElement.title,
                    "moj album title",
                    firstElement.thumbnailUrl
                )
                mockList.value?.add(listitem)
            }*/

            //    val albumsResponse = albumRepository.getAllAlbums(allPhotos.value.)
        }
    }
}