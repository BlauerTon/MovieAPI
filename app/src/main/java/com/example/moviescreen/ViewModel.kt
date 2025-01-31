package com.example.moviescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val popularMovies = RetrofitClient.apiService.getPopularMovies()
                val moviesWithDetails = popularMovies.movies.map { movie ->
                    val details = RetrofitClient.apiService.getMovieDetails(movie.id)
                    movie.copy(
                        duration = "${details.runtime ?: 0} Minutes",
                        genre = details.genres?.joinToString(" | ") { it.name ?: "" } ?: "Unknown"
                    )
                }
                _movies.value = moviesWithDetails
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}