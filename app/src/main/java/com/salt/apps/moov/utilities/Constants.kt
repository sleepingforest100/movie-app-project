package com.salt.apps.moov.utilities

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original"
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYzZjNzYxMThhYTQ2NjRhNWFmNTYxZGJiMjUyNDZhYiIsInN1YiI6IjY1N2JmMjM4N2VjZDI4MDBhZDI0Nzg1YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zUErmoVx4ZJ9CavnzfvqXBAIc1ZLbrFaIxuS-fYMJJo"
    private const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"

    fun getBearer(): String {
        return BEARER + API_KEY
    }

    fun getImageUrl(path: String): String {
        return BASE_URL_IMAGE + path
    }

    const val DB_NAME = "Moov.db"
    const val DB_VERSION = 2
}