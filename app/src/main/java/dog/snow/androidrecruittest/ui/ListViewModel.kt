package dog.snow.androidrecruittest.ui

import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.ui.model.Detail
import dog.snow.androidrecruittest.ui.model.ListItem

class ListViewModel : ViewModel() {

    private val allPhotos = mutableListOf<RawPhoto>()
    private val allAlbums = mutableListOf<RawAlbum>()
    private val allUsers = mutableListOf<RawUser>()

    fun setListsFromIntent(photos: List<RawPhoto>, albums: List<RawAlbum>, users: List<RawUser>) {
        allPhotos.addAll(photos)
        allAlbums.addAll(albums)
        allUsers.addAll(users)
    }

    fun getItemList(): List<ListItem> {
        val itemList = mutableListOf<ListItem>()
        allPhotos.forEach { photo ->
            val filteredAlbum = allAlbums.filter { album ->
                album.id == photo.albumId
            }
            itemList.add(
                ListItem(
                    photo.id,
                    photo.title,
                    filteredAlbum.first().title,
                    photo.thumbnailUrl
                )
            )
        }

        return itemList.toList()
    }

    fun getDetail(item: ListItem): Detail {
        val photo = allPhotos.first { photo ->
            photo.id == item.id
        }
        val album = allAlbums.first { album ->
            album.id == photo.albumId
        }
        val user = allUsers.first { user ->
            user.id == album.userId
        }

        return Detail(
            photo.id,
            photo.title,
            album.title,
            user.username,
            user.email,
            user.phone,
            photo.thumbnailUrl
        )
    }
}