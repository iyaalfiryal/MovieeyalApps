package com.iyaliyul.movieeyalapps.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.iyaliyul.movieeyalapps.R
import com.iyaliyul.movieeyalapps.data.model.MovieItem
import com.iyaliyul.movieeyalapps.databinding.ActivityDetailBinding
import com.iyaliyul.movieeyalapps.utils.Constants
import com.iyaliyul.movieeyalapps.utils.loadImage

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by viewBinding()
    private var movieItem: MovieItem? = null

    companion object{
        const val DETAIL_EXTRA = "detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieItem = intent?.extras?.getParcelable(DETAIL_EXTRA)
        movieItem?.let {
            binding.apply {
                ivDetailMovieBackdrop.loadImage(Constants.URL_IMAGE + it.backdrop_path)
                ivDetailMoviePoster.loadImage(Constants.URL_IMAGE + it.poster_path)
                tvDetailMovieTitle.text = it.original_title
                tvDetailMovieRate.text = it.vote_average.toString()
                tvDetailMovieRelease.text = it.release_date
                tvDetailMovieSynopsisInside.text = it.overview
            }
        }
    }
}