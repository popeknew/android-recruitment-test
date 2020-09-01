package dog.snow.androidrecruittest

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setThumbnail")
fun ImageView.setThumbnail(thumbnail: String) {
    Glide
        .with(this)
        .load("https://toysfun.pl/product-pol-20426-Schleich-Piesek-Cocker-Spaniel.html")
        .centerCrop()
        .into(this)
}