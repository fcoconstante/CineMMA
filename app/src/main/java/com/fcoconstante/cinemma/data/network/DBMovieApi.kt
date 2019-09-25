package com.fcoconstante.cinemma.data.network


import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DBMovieApi {

    @GET("movie/?api_key=a5352fe37b12cdf53ef60b43faa05703")
    suspend fun getMovies() : Response<MoviesResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): DBMovieApi {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                 .baseUrl("https://api.themoviedb.org/3/discover/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DBMovieApi::class.java)
        }
    }

    /*
    internal var url = "https://api.themoviedb.org/3/discover/movie/?api_key=a5352fe37b12cdf53ef60b43faa05703"
    internal var urlTren = "https://api.themoviedb.org/3/trending/movie/week?api_key=a5352fe37b12cdf53ef60b43faa05703"
    internal var urlRecent = "https://api.themoviedb.org/3/discover/movie/?api_key=a5352fe37b12cdf53ef60b43faa05703&certification_country=US&certification.lte=G&sort_by=popularity.desc"
    */


}