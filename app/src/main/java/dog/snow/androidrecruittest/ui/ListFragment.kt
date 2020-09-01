package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dog.snow.androidrecruittest.ALBUMS
import dog.snow.androidrecruittest.PHOTOS
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.USERS
import dog.snow.androidrecruittest.ext.toAlbumList
import dog.snow.androidrecruittest.ext.toPhotoList
import dog.snow.androidrecruittest.ext.toUserList
import dog.snow.androidrecruittest.ui.adapter.ListAdapter
import dog.snow.androidrecruittest.ui.model.ListItem
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_list), ListAdapter.Interaction {

    private lateinit var adapter: ListAdapter
    private val viewModel: ListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photos = arguments?.getParcelableArray(PHOTOS).toPhotoList()
        val albums = arguments?.getParcelableArray(ALBUMS).toAlbumList()
        val users = arguments?.getParcelableArray(USERS).toUserList()

        viewModel.setListsFromIntent(photos, albums, users)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = ListAdapter(this)
        rv_items.adapter = adapter
        adapter.submitList(viewModel.getItemList())
    }

    override fun onItemSelected(position: Int, item: ListItem) {
        val action =
            ListFragmentDirections.actionListFragmentToDetailsFragment(viewModel.getDetail(item))
        findNavController().navigate(action)
    }
}