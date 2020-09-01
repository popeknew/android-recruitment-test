package dog.snow.androidrecruittest

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setThumbnaill")
fun ImageView.setThumbnaill(thumbnail: String) {
    Glide
        .with(this)
        .load(thumbnail)
        .centerCrop()
        .into(this)
}