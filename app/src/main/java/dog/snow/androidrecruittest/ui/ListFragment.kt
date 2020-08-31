package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.adapter.ListAdapter
import dog.snow.androidrecruittest.ui.adapter.ListAdapterr
import dog.snow.androidrecruittest.ui.model.ListItem
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(R.layout.list_fragment), ListAdapter.Interaction {

    private lateinit var adapter: ListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        adapter = ListAdapter()
        rv_items.adapter = adapter
    }

    override fun onItemSelected(position: Int, item: ListItem) {
        val przeslane = arguments?.getParcelableArray("bundle")
        przeslane?.forEach { element ->
            Log.d("itemy", "$element")
        }
    }
}