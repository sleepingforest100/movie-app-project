package com.salt.apps.moov.data.source.remote.network

// Определение герметичного класса для представления ответа API при работе с данными о фильмах.
sealed class MovieApiResponse<out R> {
    // Успешный ответ API, содержащий данные о фильмах типа T.
    data class Success<out T>(val data: T) : MovieApiResponse<T>()

    // Ответ об ошибке, содержащий строку с сообщением об ошибке.
    data class Error(val errorMessage: String) : MovieApiResponse<Nothing>()

    // Представление пустого ответа, когда данных для возврата нет.
    object Empty : MovieApiResponse<Nothing>()
}
