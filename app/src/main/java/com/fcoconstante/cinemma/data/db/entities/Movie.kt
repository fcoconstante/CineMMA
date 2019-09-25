package com.fcoconstante.cinemma.data.db.entities

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter



@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val popularity: Double,
    val vote_count: Int,
    val video: Boolean,
    val poster_path: String,
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String):Serializable

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().centerInside())
        .into(view)
}