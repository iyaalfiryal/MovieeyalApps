package com.iyaliyul.movieeyalapps.data.repository

import com.iyaliyul.movieeyalapps.data.model.MovieResponse
import com.iyaliyul.movieeyalapps.data.source.APIService
import com.iyaliyul.movieeyalapps.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: APIService){
    suspend fun getDiscoverMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        page: Int,
        year: Int
    ): Flow<Resource<MovieResponse>>{
        return flow {
            val response = apiService.getDiscoverMovies(apiKey, language, sortBy, includeAdult, page, year)

            try {
                if (response.results.isNotEmpty()){
                    emit(Resource.Success(response))
                }else{
                    emit(Resource.Empty)
                }
            }catch (e: HttpException){
                emit(Resource.Error(e.toString(), response))
            }
        }
    }
}