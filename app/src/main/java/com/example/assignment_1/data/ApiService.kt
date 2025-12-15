package com.example.assignment_1.data

import retrofit2.http.GET

interface ApiService {
    @GET("14a27bd0bcd0cd323b35ad79cf3b493dddf6216b/videos.json")
    suspend fun getVideos(): List<Video>

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/poudyalanil/ca84582cbeb4fc123a13290a586da925/raw/"
    }
}
