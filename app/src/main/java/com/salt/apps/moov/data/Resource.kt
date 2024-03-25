package com.salt.apps.moov.data

// Запечатанный класс Resource представляет состояние данных, которые могут быть загружены,
// успешно получены или содержать ошибку.
sealed class Resource<out R> {
    // Представляет состояние загрузки. Опционально может содержать предыдущие данные.
    data class Loading<out T>(val data: T? = null) : Resource<T>()

    // Представляет успешное получение данных. Содержит данные типа T.
    data class Success<out T>(val data: T) : Resource<T>()

    // Представляет состояние с ошибкой. Может содержать сообщение об ошибке.
    data class Error(val message: String? = null) : Resource<Nothing>()
}
