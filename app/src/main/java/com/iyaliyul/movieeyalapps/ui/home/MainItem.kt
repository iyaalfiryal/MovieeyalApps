package com.iyaliyul.movieeyalapps.ui.home

import android.view.View
import com.iyaliyul.movieeyalapps.R
import com.iyaliyul.movieeyalapps.data.model.MovieItem
import com.iyaliyul.movieeyalapps.databinding.MovieListItemBinding
import com.iyaliyul.movieeyalapps.utils.Constants
import com.iyaliyul.movieeyalapps.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class MainItem(
    private val movie: MovieItem,
    private val onclick: (MovieItem) -> Unit

): BindableItem<MovieListItemBinding>(){
    override fun bind(viewBinding: MovieListItemBinding, position: Int) {

        viewBinding.apply {
//            movie.backdrop_path?.let { itemImg.loadImage(Constants.URL_IMAGE + it) }
            itemName.text = movie.original_title
            itemImg.loadImage(Constants.URL_IMAGE + movie.backdrop_path)
            itemCard.setOnClickListener {
                onclick(movie)
            }
        }
    }

    override fun getLayout(): Int = R.layout.movie_list_item

    override fun initializeViewBinding(view: View): MovieListItemBinding {
        return MovieListItemBinding.bind(view)
    }
}