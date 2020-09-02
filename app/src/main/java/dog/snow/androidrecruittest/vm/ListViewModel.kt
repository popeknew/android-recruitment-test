package dog.snow.androidrecruittest.vm

import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.model.RawAlbum
import dog.snow.androidrecruittest.model.RawPhoto
import dog.snow.androidrecruittest.model.RawUser
import dog.snow.androidrecruittest.model.Detail
import dog.snow.androidrecruittest.model.ListItem

class ListViewModel : ViewModel() {

    private val allPhotos = mutableListOf<RawPhoto>()
    private val allAlbums = mutableListOf<RawAlbum>()
    private val allUsers = mutableListOf<RawUser>()
    private val itemList = mutableListOf<ListItem>()

    fun setListsFromIntent(photos: List<RawPhoto>, albums: List<RawAlbum>, users: List<RawUser>) {
        allPhotos.addAll(photos)
        allAlbums.addAll(albums)
        allUsers.addAll(users)
    }

    fun getItemList(): List<ListItem> {
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

    fun getFilteredItemList(text: String): List<ListItem> {
        return itemList.filter { item ->
            item.title.contains(text) || item.albumTitle.contains(text)
        }
    }
}