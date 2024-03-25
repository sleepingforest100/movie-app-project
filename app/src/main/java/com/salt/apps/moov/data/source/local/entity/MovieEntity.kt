package com.salt.apps.moov.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moov")
data class MovieEntity(
    @PrimaryKey
    var id: Int,
    val overview: String? = null,
    var backdropPath: String? = null,
    var posterPath: String? = null,
    var originalLanguage: String,
    var releaseDate: String? = null,
    var voteCount: Int? = null,
    var voteAverage: Double? = null,
    var genreIds: List<Int?>,
    var movieType: String? = null,
    var title: String,
    var isFavorite: Boolean = false
)