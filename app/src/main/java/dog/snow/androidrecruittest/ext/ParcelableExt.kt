package dog.snow.androidrecruittest.ext

import android.os.Parcelable
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

fun Array<Parcelable>?.toPhotosList(): List<RawPhoto> {
    val photoList = mutableListOf<RawPhoto>()
    this?.forEach { photo ->
        photo as RawPhoto
        photoList.add(photo)
    }

    return photoList
}

fun Array<Parcelable>.toAlbumsList(): List<RawAlbum> {
    val albumList = mutableListOf<RawAlbum>()
    forEach { album ->
        album as RawAlbum
        albumList.add(album)
    }

    return albumList
}

fun Array<Parcelable>.toUsersList(): List<RawUser> {
    val userList = mutableListOf<RawUser>()
    forEach { user ->
        user as RawUser
        userList.add(user)
    }

    return userList
}