package dog.snow.androidrecruittest.ui

import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

class ListViewModel : ViewModel() {

    val allPhotos = mutableListOf<RawPhoto>()
    val allAlbums = mutableListOf<RawAlbum>()
    val allUsers = mutableListOf<RawUser>()

    fun setListsFromIntent(photos: List<RawPhoto>, albums: List<RawAlbum>, users: List<RawUser>) {
        allPhotos.addAll(photos)
        allAlbums.addAll(albums)
        allUsers.addAll(users)
    }
}