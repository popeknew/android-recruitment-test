package dog.snow.androidrecruittest

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

@BindingAdapter("setThumbnail")
fun ImageView.setThumbnail(thumbnail: String) {

    val url = GlideUrl(
        thumbnail, LazyHeaders.Builder().addHeader("User-Agent", "your-user-agent").build()
    )
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .centerCrop()
        .into(this)
}