package com.example.boilerplate.core.network.interceptor

interface TokenProvider {
    fun getToken(): String?
}
