package dog.snow.androidrecruittest.ext

import android.os.Parcelable
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser

fun Array<Parcelable>?.toPhotoList(): List<RawPhoto> {
    val photoList = mutableListOf<RawPhoto>()
    this?.forEach { photo ->
        photo as RawPhoto
        photoList.add(photo)
    }

    return photoList
}

fun Array<Parcelable>?.toAlbumList(): List<RawAlbum> {
    val albumList = mutableListOf<RawAlbum>()
    this?.forEach { album ->
        album as RawAlbum
        albumList.add(album)
    }

    return albumList
}

fun Array<Parcelable>?.toUserList(): List<RawUser> {
    val userList = mutableListOf<RawUser>()
    this?.forEach { user ->
        user as RawUser
        userList.add(user)
    }

    return userList
}