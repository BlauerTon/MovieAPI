package com.example.moviescreen


import com.example.moviescreen.APIConfig.API_KEY
import com.example.moviescreen.APIConfig.TMDB_BASE_URL
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query




interface TMDBApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String = API_KEY): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetailsResponse
}

data class MovieDetailsResponse(
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("genres") val genres: List<Genre>?
)

data class Genre(
    @SerializedName("name") val name: String?
)

// Retrofit instance
object RetrofitClient {
    val apiService: TMDBApiService by lazy {
        Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApiService::class.java)
    }
}
