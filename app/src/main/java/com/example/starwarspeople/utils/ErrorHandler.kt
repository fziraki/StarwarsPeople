package com.example.starwarspeople.utils

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity
}