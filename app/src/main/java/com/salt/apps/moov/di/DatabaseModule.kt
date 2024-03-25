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

@Module // Аннотация Dagger модуля, объявляет класс как модуль Dagger, который предоставляет зависимости.
@InstallIn(SingletonComponent::class) // Указывает, что зависимости предоставляются в области SingletonComponent.
object DatabaseModule {
    // Функция для предоставления экземпляра базы данных.
    @Provides // Указывает Dagger, что этот метод предоставляет зависимость.
    @Singleton // Указывает, что предоставляемый объект будет существовать в единственном экземпляре.
    fun provideDatabase(
        @ApplicationContext context: Context // Контекст приложения для создания базы данных.
    ): MovieDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MovieDatabase::class.java, // Класс базы данных Room.
            name = DB_NAME // Имя файла базы данных.
        ).fallbackToDestructiveMigration() // Разрушающая миграция при изменении версии схемы базы данных.
            .build() // Создание базы данных.
    }

    // Функция для предоставления DAO для доступа к данным в базе данных.
    @Provides // Указывает Dagger, что этот метод предоставляет зависимость.
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao() // Получение MovieDao из MovieDatabase.
}
