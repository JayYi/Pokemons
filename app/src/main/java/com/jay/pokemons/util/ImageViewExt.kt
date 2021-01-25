package com.jay.pokemons.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:loadUrl")
fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
            .fitCenter()
            .into(this)
    }
}