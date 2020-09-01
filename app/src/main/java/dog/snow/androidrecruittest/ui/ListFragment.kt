package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.ui.adapter.ListAdapter
import dog.snow.androidrecruittest.ui.model.Detail
import dog.snow.androidrecruittest.ui.model.ListItem
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_list), ListAdapter.Interaction {

    private lateinit var adapter: ListAdapter
    private val viewModel: ListViewModel by viewModel()
    private val allPhotos = mutableListOf<RawPhoto>()
    private val allAlbums = mutableListOf<RawAlbum>()
    private val allUsers = mutableListOf<RawUser>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()


        val photos = arguments?.getParcelableArray("photos")
        photos?.forEach { photo ->
            photo as RawPhoto
            allPhotos.add(photo)
        }

        val albums = arguments?.getParcelableArray("albums")
        albums?.forEach { album ->
            album as RawAlbum
            allAlbums.add(album)
        }

        val users = arguments?.getParcelableArray("users")
        users?.forEach { user ->
            user as RawUser
            allUsers.add(user)
        }

        // viewModel.setListsFromIntent(photos?.toPhotosList(), albums, users)

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

        adapter.submitList(itemList.toList())
    }

    private fun setUpRecyclerView() {
        adapter = ListAdapter(this)
        rv_items.adapter = adapter
    }

    private fun getIntents(
        photos: Array<Parcelable>,
        albums: Array<Parcelable>,
        users: Array<Parcelable>
    ) {

    }

    override fun onItemSelected(position: Int, item: ListItem) {
        val photo = allPhotos.first { photo ->
            photo.id == item.id
        }
        val album = allAlbums.first { album ->
            album.id == photo.albumId
        }
        val user = allUsers.first { user ->
            user.id == album.userId
        }
        val detail = Detail(photo.id, photo.title, album.title, user.username, user.email, user.phone, photo.thumbnailUrl)
        Log.d(TAG, "onItemSelected: $item")
        Log.d(TAG, "onItemSelected: ${detail.url}")
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(detail)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "ListFragment"
    }
}