package com.example.matthiastison.emotionsapplication.Network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

// The interface which provides methods to get result of the Unsplash webservice
interface UnsplashApi {

    // Get photo's on search from the API
    @GET("search/photos")
    fun getPhotos(@Query("query") query : String,
                  @Query("page") page: Int = 1,
                  @Query("per_page") per_page: Int = 20): Observable<SearchResult>

}