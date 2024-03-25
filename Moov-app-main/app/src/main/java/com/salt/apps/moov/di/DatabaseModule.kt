package com.salt.apps.moov.di

import android.content.Context
import androidx.room.Room
import com.salt.apps.moov.data.source.local.room.MovieDao
import com.salt.apps.moov.data.source.local.room.MovieDatabase
import com.salt.apps.moov.utilities.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MovieDatabase::class.java,
            name = DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
}