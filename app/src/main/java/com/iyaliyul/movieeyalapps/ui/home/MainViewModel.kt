package com.iyaliyul.movieeyalapps.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iyaliyul.movieeyalapps.data.model.MovieResponse
import com.iyaliyul.movieeyalapps.data.repository.MovieRepository
import com.iyaliyul.movieeyalapps.utils.Constants
import com.iyaliyul.movieeyalapps.utils.Resource
import com.iyaliyul.movieeyalapps.utils.getCurrentData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    private val _movieData = MutableLiveData<Resource<MovieResponse>>()
    val movieData = _movieData

    init {
        getDiscoverMovies()
    }

    private fun getDiscoverMovies(page: Int = 1) = viewModelScope.launch {
        _movieData.value = Resource.Loading
        repository.getDiscoverMovies(
            Constants.API_KEY,
            Constants.LANG,
            Constants.SORT_BY,
            false,
            page,
            getCurrentData("yyyy").toInt()
        ).collect { movie ->
            _movieData.value = movie
        }
    }
}