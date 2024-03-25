package com.salt.apps.moov.data.model

// Defines a data class named 'Movie' to hold information about individual movies.
data class Movie(
    var id: Int, // Unique identifier for the movie.
    val overview: String? = null, // A brief description of the movie. Nullable to allow for movies without an overview.
    var backdropPath: String? = null, // Path to the movie's backdrop image. Nullable for cases where the image is not available.
    var posterPath: String? = null, // Path to the movie's poster image. Also nullable for the same reason as backdropPath.
    var originalLanguage: String, // The language in which the movie was originally made.
    var releaseDate: String? = null, // The release date of the movie. Nullable because not all movies might have a known release date.
    var voteCount: Int? = null, // The number of votes the movie has received. Nullable to accommodate movies without votes.
    var voteAverage: Double? = null, // The average rating of the movie based on votes. Nullable for movies without ratings.
    var genreIds: List<Int?>, // A list of genre IDs associated with the movie. Can contain nulls if some genres are unspecified.
    var moovType: String? = null, // A custom field to categorize the movie further, e.g., "Action", "Comedy". Nullable for flexibility.
    var title: String, // The title of the movie.
    var isFavorite: Boolean = false // A flag to indicate whether the movie is marked as a favorite by the user. Defaults to false.
)
